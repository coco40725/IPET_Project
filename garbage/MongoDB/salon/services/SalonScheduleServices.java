package com.ipet.web.salon.services;


import com.ipet.web.salon.entities.*;
import com.ipet.web.salon.repositories.*;
import com.ipet.web.salon.utils.SalonUtils;
import com.ipet.web.staff.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;


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

    private SalonUtils salonUtils;

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
    public void setSalonUtils(SalonUtils salonUtils){
        this.salonUtils = salonUtils;
    }

    // job add
    @Transactional
    public String addSchedule(List<SalonSchedule> salonSchedules){

        // 先執行 mysql事務，再執行 mongodb 事務
        for (SalonSchedule job : salonSchedules) {
            Date schDate = job.getSchDate();
            String schPeriod = job.getSchPeriod();
            String groomerID = job.getGroomer().getId();
            String asstID1 = job.getAsst1().getId();
            String asstID2 = job.getAsst2().getId();
            System.out.println(job);

            List<SalonSchedule> schByDatePeriod = salonScheduleRepository.findAllBySchDateAndSchPeriod(schDate, schPeriod);
            Set<String> empsByDatePeriod = findEmpsByDatePeriod(schDate, schPeriod);

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
        return "success";
    }


    // job delete
    @Transactional
    public String deleteSchedule(String id){
        // 必須沒有預約單才可以刪除
        // 先執行 mysql事務，再執行 mongodb 事務
        SalonAppointment appointBySchId = salonAppointmentRepository.findBySchId(id);
        if (appointBySchId != null){
            return "該班表已被預約，不可刪除!";
        }
        salonScheduleRepository.deleteById(id);
        return "success";
    }


    // job edit
    @Transactional
    public String editSchedule(SalonSchedule newJobSchedule){

        // 只可以更動 美容師 助理 以及 備註
        String schID = newJobSchedule.getId();
        String groomerID = newJobSchedule.getGroomer().getId();
        String asstID1 = newJobSchedule.getAsst1().getId();
        String asstID2 = newJobSchedule.getAsst2().getId();
        Date newSchDate = newJobSchedule.getSchDate();
        String newSchPeriod = newJobSchedule.getSchPeriod();
        Set<String> empsByDatePeriodExcludedSchID = findEmpsByDatePeriod(newSchDate, newSchPeriod, schID);


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

        return "success";
    }

    // job query
    public List<SalonSchedule> getAllSchedule(){
        System.out.println(salonUtils.joinSchedule(salonScheduleRepository.findAll()));
        return salonUtils.joinSchedule(salonScheduleRepository.findAll());
    }
    public SalonSchedule getScheduleById(String id){
        List<SalonSchedule> salonSchedules = new ArrayList<>();
        salonSchedules.add(salonScheduleRepository.findById(id).orElse(null));
        return salonUtils.joinSchedule(salonSchedules).get(0);
    }
    public List<SalonSchedule> findAvailableJobsToAddAppoint() {
        List<SalonSchedule> all = new ArrayList<>();
        Date today = new Date(System.currentTimeMillis());
        for (SalonSchedule job : salonUtils.joinSchedule(salonScheduleRepository.findAll())){
            if (job.getSalonAppointment() == null && (job.getSchDate().after(today) || job.getSchDate().equals(today))){
                all.add(job);
            }
        }
        return all;
    }
    public Set<Date> findIllegalDates(String period, String groomerID, String asstID1, String asstID2){
        // findIllegalDatesToAddJobs
        // 選定 美容師, 助理1, 助理2 與時段後
        // 回傳以今天後的3個月，哪幾個日期
        // 1. 班表數量 = 2
        // 2. 或 有重複的員工
        Set<Date> illegalDates = new HashSet<>();
        Map<Date, Integer> map = new HashMap<>();
        List<SalonSchedule> allBySchDateAfterAndSchPeriod = salonScheduleRepository.findAllBySchDateAfterAndSchPeriod(new Date(), period);
        for (SalonSchedule salonSchedule : allBySchDateAfterAndSchPeriod) {
            if (salonSchedule.getGroomer().getId().equals(groomerID) ||
                    salonSchedule.getAsst1().getId().equals(asstID1) ||
                    salonSchedule.getAsst1().getId().equals(asstID2) ||
                    salonSchedule.getAsst2().getId().equals(asstID1) ||
                    salonSchedule.getAsst2().getId().equals(asstID2)){
                illegalDates.add(salonSchedule.getSchDate());
            }
            map.put(salonSchedule.getSchDate(), map.getOrDefault(salonSchedule.getSchDate(), 1) + 1);
        }

        for (Map.Entry<Date, Integer> entry: map.entrySet()){
            if (entry.getValue() == 2) {
                illegalDates.add(entry.getKey());
            }
        }


        return illegalDates;
    }
    public Set<String> findEmpsByDatePeriod(Date date, String period, String excludedschID) {
        Set<String> emps = new HashSet<>();
        List<SalonSchedule> list = salonScheduleRepository.findAllBySchDateAndSchPeriodAndIdNot(date, period, excludedschID);
        for (SalonSchedule job : list){
            emps.add(job.getGroomer().getId());
            emps.add(job.getAsst1().getId());
            emps.add(job.getAsst2().getId());
        }
        return emps;
    }
    public Set<String> findEmpsByDatePeriod(Date date, String period) {
        Set<String> emps = new HashSet<>();
        List<SalonSchedule> list = salonScheduleRepository.findAllBySchDateAndSchPeriod(date, period);
        for (SalonSchedule job : list){
            emps.add(job.getGroomer().getId());
            emps.add(job.getAsst1().getId());
            emps.add(job.getAsst2().getId());
        }
        return emps;
    }

}
