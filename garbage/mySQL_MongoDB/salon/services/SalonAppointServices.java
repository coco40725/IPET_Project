package temp.salon.services;


import com.ipet.web.salon.utils.SalonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.*;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 11:25
 */
@Service
public class SalonAppointServices {
    private SalonAppointmentRepository salonAppointmentRepository;
    private SalonAppointmentDetailRepository salonAppointmentDetailRepository;
    private SalonServiceRepository salonServiceRepository;
    private SalonScheduleRepository salonScheduleRepository;
    private SalonAppointmentQueryRepository appointmentQueryRepository;
    private SalonScheduleQueryRepository salonScheduleQueryRepository;
    @Autowired
    public void setSalonAppointmentRepository(SalonAppointmentRepository salonAppointmentRepository) {
        this.salonAppointmentRepository = salonAppointmentRepository;
    }
    @Autowired
    public void setSalonAppointmentDetailRepository(SalonAppointmentDetailRepository salonAppointmentDetailRepository) {
        this.salonAppointmentDetailRepository = salonAppointmentDetailRepository;
    }
    @Autowired
    public void setSalonServiceRepository(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }
    @Autowired
    public void setSalonScheduleRepository(SalonScheduleRepository salonScheduleRepository) {
        this.salonScheduleRepository = salonScheduleRepository;
    }
    @Autowired
    public void setAppointmentQueryRepository(SalonAppointmentQueryRepository appointmentQueryRepository) {
        this.appointmentQueryRepository = appointmentQueryRepository;
    }
    @Autowired
    public void setSalonScheduleQueryRepository(SalonScheduleQueryRepository salonScheduleQueryRepository) {
        this.salonScheduleQueryRepository = salonScheduleQueryRepository;
    }

    // Appointment add
    @Transactional
    public String addAppointment(SalonAppointment appointment){
        // 0. 獲得對應的班表 與 Appointment的服務細項
        SalonSchedule job = salonScheduleRepository.findById(appointment.getSchId()).orElse(null);
        List<SalonAppointmentDetail> apps = salonAppointmentDetailRepository.findAllByApmId(appointment.getApmId());


        // 1. 判斷此預約所預定的班表是否尚未被預約
        assert job != null;
        if (job.getAppointId() != null){
            return "新增失敗，該時段已被其他人預約";
        }

        // 2. 判斷此預約是否只預約一種 Service category
        Set<Integer> serviceCategoryIds = new HashSet<>();
        for (SalonAppointmentDetail appointmentDetail : apps) {
            Integer svcID = appointmentDetail.getSvcId();
            serviceCategoryIds.add(Objects.requireNonNull(salonServiceRepository.findById(svcID).orElse(null)).getTypeId());
        }

        if (serviceCategoryIds.size() != 1) {
            return "新增失敗，服務類別數量異常";
        }

        // 3. 將資料加進 Appointment Table，並獲得 apmId
        salonAppointmentRepository.save(appointment);

        // 4. 更新 jobschedule: 將 ApmID 加進 job schedule中
        job.setAppointId(appointment.getApmId());
        salonScheduleRepository.save(job);

        // 4. 新增 AppointmentDetail: 將 Appointment 的服務細項新增至 AppointmentDetail Table
        salonAppointmentDetailRepository.saveAll(apps);

        // 先執行 mysql事務，再執行 mongodb 事務
        salonAppointmentRepository.save(appointment);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_COMMITTED){
                    addAppointForQuery(appointment);
                }
            }
        });
        return "success";
    }
    @Transactional
    public void addAppointForQuery(SalonAppointment appointment){
        SalonSchedule salonSchedule = Objects.requireNonNull(salonScheduleRepository.findById(appointment.getSchId()).orElse(null));
        appointmentQueryRepository.save(SalonUtils.integrateSalonAppointmentQuery(appointment));
        salonScheduleQueryRepository.save(SalonUtils.integrateSalonScheduleQuery(salonSchedule));
    }


    // Appointment delete
    @Transactional
    public String deleteAppointment(Integer id){
        // 先執行 mysql事務，再執行 mongodb 事務
        salonAppointmentRepository.deleteById(id);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_COMMITTED){
                    deleteAppointForQuery(id);
                }
            }
        });
        return "success";
    }
    @Transactional
    public void deleteAppointForQuery(Integer id){
        appointmentQueryRepository.deleteById(id);
    }



    // Appointment edit
    @Transactional
    public String editAppointment(SalonAppointment newAppointment){
        SalonAppointment oldAppointment = salonAppointmentRepository.findById(newAppointment.getApmId()).orElse(null);
        SalonSchedule originalJob = salonScheduleRepository.findById(Objects.requireNonNull(oldAppointment).getSchId()).orElse(null);
        SalonSchedule newSelectJob = salonScheduleRepository.findById(newAppointment.getSchId()).orElse(null);

        if (newSelectJob == null || originalJob == null){
            return "修改失敗，查無此班表或預約單";
        }



        // 1. 僅允許使用者修改 schID  預約狀態 與 note (訂單建立時間會轉變成 修改時間，因此允許不同)，
        // 不可更動其他欄位: apmID, MemID, PetID, TotalPrice
        // 確認其他欄位與之前相同，以避開資料被 null覆蓋
        if (!newAppointment.getApmId().equals(oldAppointment.getApmId()) ||
                !newAppointment.getMemId().equals(oldAppointment.getMemId()) ||
                !newAppointment.getPetId().equals(oldAppointment.getPetId()) ||
                !newAppointment.getTotalPrice().equals(oldAppointment.getTotalPrice())){
            return "修改失敗，只可修改班表、預約狀態與備註。";
        }

        // 2. 當原狀態為 "已支付訂金" 才可以修改班表與狀態
        if (oldAppointment.getApmStatus() != 0){
            if (!oldAppointment.getApmStatus().equals(newAppointment.getApmStatus()) ||
                    !oldAppointment.getSchId().equals(newAppointment.getSchId())) {
                return "修改失敗，預約狀態不是 已支付訂金，故不可修改狀與班表。";
            }
        }


        // 3. 通過上面的檢查，開始進行資料update

        // 3.1 更改班表 (狀態必須為 已支付訂金)
        if (!oldAppointment.getSchId().equals(newAppointment.getSchId())){
            //  "新班表" 是否已被預約
            if (newSelectJob.getAppointId() != null) {
                return "修改失敗，此時段與日期已被預約。";
            }else {
                originalJob.setAppointId(null);
                newSelectJob.setAppointId(newAppointment.getApmId());
                salonScheduleRepository.save(originalJob);
                salonScheduleRepository.save(newSelectJob);
                salonAppointmentRepository.save(newAppointment);
            }
            // 3.2 更改狀態
        } else {
            // 3.2.1 改為已完成 或 逾時未到 (不用更新班表)
            if (newAppointment.getApmStatus().equals(1) || newAppointment.getApmStatus().equals(3)){
                salonAppointmentRepository.save(newAppointment);

                // 3.2.2 改為已取消 (需要更新班表)
            } else if (newAppointment.getApmStatus().equals(2)){
                originalJob.setAppointId(null);
                salonScheduleRepository.save(originalJob);
                salonAppointmentRepository.save(newAppointment);

                // 3.3 狀態與班表都未更新
            }else if (oldAppointment.getApmStatus().equals(newAppointment.getApmStatus())){
                salonAppointmentRepository.save(newAppointment);
            }
        }


        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                System.out.println("before");
                if (status == TransactionSynchronization.STATUS_COMMITTED){
                    System.out.println("enter");
                    editAppointForQuery(newAppointment, originalJob, newSelectJob);
                }
            }
        });
        return "success";
    }
    @Transactional
    public void editAppointForQuery(SalonAppointment newAppointment, SalonSchedule originalJob, SalonSchedule newSelectJob){
        appointmentQueryRepository.save(SalonUtils.integrateSalonAppointmentQuery(newAppointment));
        System.out.println(originalJob);
        salonScheduleQueryRepository.save(SalonUtils.integrateSalonScheduleQuery(originalJob));
        System.out.println(newSelectJob);
        salonScheduleQueryRepository.save(SalonUtils.integrateSalonScheduleQuery(newSelectJob));
    }





    // Appointment query
    public List<SalonAppointmentQuery> getAllAppointment(){
        return appointmentQueryRepository.findAll();
    }
    public List<SalonAppointmentQuery> getAppointByApmStatus(Integer apmStatus){
        return appointmentQueryRepository.findByApmStatus(apmStatus);
    }
    public SalonAppointmentQuery getAppointById(Integer id){
        return appointmentQueryRepository.findById(id).orElse(null);
    }

    public  List<SalonAppointment> findApp(){
        return salonAppointmentRepository.findAll();
    }

}
