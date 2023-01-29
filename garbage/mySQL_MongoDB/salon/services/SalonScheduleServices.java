package temp.salon.services;


import com.ipet.web.salon.utils.SalonUtils;
import com.ipet.web.staff.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Date;
import java.util.*;

/**
 * @author Yu-Jing
 * @create 2023-01-23-下午 07:19
 */
@Service
public class SalonScheduleServices {
    private SalonAppointmentRepository salonAppointmentRepository;
    private SalonScheduleRepository salonScheduleRepository;
    private StaffRepository staffRepository;
    private SalonScheduleQueryRepository salonScheduleQueryRepository;

    @Autowired
    public void setSalonAppointmentRepository(SalonAppointmentRepository salonAppointmentRepository) {
        this.salonAppointmentRepository = salonAppointmentRepository;
    }
    @Autowired
    public void setSalonScheduleRepository(SalonScheduleRepository salonScheduleRepository) {
        this.salonScheduleRepository = salonScheduleRepository;
    }
    @Autowired
    public void setStaffRepository(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    @Autowired
    public void setSalonScheduleQueryRepository(SalonScheduleQueryRepository salonScheduleQueryRepository) {
        this.salonScheduleQueryRepository = salonScheduleQueryRepository;
    }

    // job add
    @Transactional
    public String addSchedule(List<SalonSchedule> salonSchedules){

        // 先執行 mysql事務，再執行 mongodb 事務
        for (SalonSchedule job : salonSchedules) {
            Date schDate = job.getSchDate();
            String schPeriod = job.getSchPeriod();
            Integer groomerID = job.getGroomerId();
            Integer asstID1 = job.getAsstId1();
            Integer asstID2 = job.getAsstId2();
            System.out.println(job);

            List<SalonSchedule> schByDatePeriod = salonScheduleRepository.findAllBySchDateAndSchPeriod(schDate, schPeriod);
            Set<Integer> empsByDatePeriod = findEmpsByDatePeriod(schDate, schPeriod);

            // 1. 判斷員工是否選擇正確
            if (!"美容師".equals(Objects.requireNonNull(staffRepository.findById(groomerID).orElse(null)).getStaffPosi()) ||
                    !"美容助理".equals(Objects.requireNonNull(staffRepository.findById(asstID1).orElse(null)).getStaffPosi()) ||
                    !"美容助理".equals(Objects.requireNonNull(staffRepository.findById(asstID2).orElse(null)).getStaffPosi())){
                return "新增失敗，員工種類錯誤。";
            }
            // 2. 同個時段與日期 是否已有別組?
            if (schByDatePeriod.size() == 2){
                return "新增失敗，美容組數量已達上限。";
            }

            // 3. 判斷是否有出現同個日期時段出現同一位員工
            if (empsByDatePeriod.contains(groomerID) || empsByDatePeriod.contains(asstID1) || empsByDatePeriod.contains(asstID2)){
                return "新增失敗，同時段與日期出現重複的員工。";
            }
        }

        salonScheduleRepository.saveAll(salonSchedules);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_COMMITTED){
                    addScheduleForQuery(salonSchedules);
                }
            }
        });
        return "success";
    }
    @Transactional
    public void addScheduleForQuery(List<SalonSchedule> salonSchedules){
        List<SalonScheduleQuery> salonScheduleQuerys = new ArrayList<>();
        for (SalonSchedule salonSchedule : salonSchedules){
            salonScheduleQuerys.add(SalonUtils.integrateSalonScheduleQuery(salonSchedule));
        }
        salonScheduleQueryRepository.saveAll(salonScheduleQuerys);
    }

    // job delete
    @Transactional
    public String deleteSchedule(Integer id){
        // 必須沒有預約單才可以刪除
        // 先執行 mysql事務，再執行 mongodb 事務
        SalonAppointment appointBySchId = salonAppointmentRepository.findBySchId(id);
        System.out.println(appointBySchId);
        if (appointBySchId != null){
            return "該班表已被預約，不可刪除!";
        }
        salonScheduleRepository.deleteById(id);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_COMMITTED){
                    deleteScheduleForQuery(id);
                }
            }
        });
        return "success";
    }
    @Transactional
    public void deleteScheduleForQuery(Integer id){
        salonScheduleQueryRepository.deleteById(id);
    }



    // job edit
    @Transactional
    public String editSchedule(SalonSchedule newJobSchedule){

        // 只可以更動 美容師 助理 以及 備註
        Integer schID = newJobSchedule.getSchId();
        Integer groomerID = newJobSchedule.getGroomerId();
        Integer asstID1 = newJobSchedule.getAsstId1();
        Integer asstID2 = newJobSchedule.getAsstId2();
        Date newSchDate = newJobSchedule.getSchDate();
        String newSchPeriod = newJobSchedule.getSchPeriod();
        Set<Integer> empsByDatePeriodExcludedSchID = findEmpsByDatePeriod(newSchDate, newSchPeriod, schID);


        if (!"美容師".equals(Objects.requireNonNull(staffRepository.findById(groomerID).orElse(null)).getStaffPosi()) ||
                !"美容助理".equals(Objects.requireNonNull(staffRepository.findById(asstID1).orElse(null)).getStaffPosi()) ||
                !"美容助理".equals(Objects.requireNonNull(staffRepository.findById(asstID2).orElse(null)).getStaffPosi())){
            return "修改失敗，員工種類錯誤。";
        }


        // 2. 判斷是否有出現
        // 同個日期時段 且為其他班表 出現同一位員工
        if (empsByDatePeriodExcludedSchID.contains(groomerID) || empsByDatePeriodExcludedSchID.contains(asstID1) || empsByDatePeriodExcludedSchID.contains(asstID2)){
            return "修改失敗，同時段與日期出現重複的員工。";
        }

        salonScheduleRepository.save(newJobSchedule);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_COMMITTED){
                    editScheduleForQuery(newJobSchedule);
                }
            }
        });

        return "success";
    }
    @Transactional
    public void editScheduleForQuery(SalonSchedule salonSchedule){
        salonScheduleQueryRepository.save(SalonUtils.integrateSalonScheduleQuery(salonSchedule));
    }



    // job query
    public List<SalonScheduleQuery> getAllSchedule(){
        return salonScheduleQueryRepository.findAll();
    }
    public SalonScheduleQuery getScheduleById(Integer id){
        return salonScheduleQueryRepository.findById(id).orElse(null);
    }
    public List<SalonScheduleQuery> findAvailableJobsToAddAppoint() {
        List<SalonScheduleQuery> all = new ArrayList<>();
        Date today = new Date(System.currentTimeMillis());
        for (SalonScheduleQuery job : salonScheduleQueryRepository.findAll()){
            if (job.getAppointId() == null && (job.getDate().after(today) || job.getDate().equals(today))){
                all.add(job);
            }
        }
        return all;
    }
    public Set<Object> findIllegalDates(Date date, String period, Integer groomerID, Integer asstID1, Integer asstID2){
        return salonScheduleRepository.findIllegalDatesToAddJobs(date, period, groomerID, asstID1, asstID2);
    }
    public Set<Integer> findEmpsByDatePeriod(Date date, String period, Integer excludedschID) {
        Set<Integer> emps = new HashSet<>();
        List<SalonSchedule> list = salonScheduleRepository.findAllBySchDateAndSchPeriodAndSchIdNot(date, period, excludedschID);
        for (SalonSchedule job : list){
            emps.add(job.getGroomerId());
            emps.add(job.getAsstId1());
            emps.add(job.getAsstId2());
        }
        return emps;
    }
    public Set<Integer> findEmpsByDatePeriod(Date date, String period) {
        Set<Integer> emps = new HashSet<>();
        List<SalonSchedule> list = salonScheduleRepository.findAllBySchDateAndSchPeriod(date, period);
        for (SalonSchedule job : list){
            emps.add(job.getGroomerId());
            emps.add(job.getAsstId1());
            emps.add(job.getAsstId2());
        }
        return emps;
    }

}
