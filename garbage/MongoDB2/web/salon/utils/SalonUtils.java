package com.ipet.web.salon.utils;


import com.ipet.web.member.repositories.MemberRepository;
import com.ipet.web.member.repositories.PetRepository;
import com.ipet.web.salon.entities.SalonAppointment;
import com.ipet.web.salon.entities.SalonSchedule;
import com.ipet.web.salon.entities.SalonService;
import com.ipet.web.salon.repositories.SalonAppointmentRepository;
import com.ipet.web.salon.repositories.SalonSaleRepository;
import com.ipet.web.salon.repositories.SalonScheduleRepository;
import com.ipet.web.salon.repositories.SalonServiceRepository;
import com.ipet.web.staff.repositories.StaffRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Yu-Jing
 * @create 2023-01-27-下午 05:41
 */
@Component
public class SalonUtils {
    private SalonServiceRepository salonServiceRepository;
    private SalonSaleRepository salonSaleRepository;
    private SalonAppointmentRepository salonAppointmentRepository;
    private MemberRepository memberRepository;
    private PetRepository petRepository;
    private SalonScheduleRepository salonScheduleRepository;
    private StaffRepository staffRepository;


    @Autowired
    public void setSalonServiceRepository(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }
    @Autowired
    public void setSalonSaleRepository(SalonSaleRepository salonSaleRepository) {
        this.salonSaleRepository = salonSaleRepository;
    }
    @Autowired
    public void setSalonAppointmentRepository(SalonAppointmentRepository salonAppointmentRepository){
        this.salonAppointmentRepository = salonAppointmentRepository;
    }
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setPetRepository(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
    @Autowired
    public void setSalonScheduleRepository(SalonScheduleRepository salonScheduleRepository) {
        this.salonScheduleRepository = salonScheduleRepository;
    }
    @Autowired
    public void setStaffRepository(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<SalonAppointment> joinAppointInfo(List<SalonAppointment> appointments){
        for (SalonAppointment appointment : appointments){
            // member
            appointment.setMem(memberRepository.findById(appointment.getMemId()).orElse(null));
            // pet
            appointment.setPet(petRepository.findById(appointment.getPetId()).orElse(null));
            // schedule
            appointment.setSch(salonScheduleRepository.findById(appointment.getSchId()).orElse(null));
            // appointmentdetail
            List<Map<String, String>> appointDetail = appointment.getSalonAppointmentDetail();
            for (Map<String, String> map : appointDetail){
                String svcName = Objects.requireNonNull(salonServiceRepository.findById(map.get("SVC_ID")).orElse(null)).getSvcName();
                map.put("svcName", svcName);
                if (!"".equals(map.get("SALE_ID"))){
                    String saleName = Objects.requireNonNull(salonSaleRepository.findById(map.get("SALE_ID")).orElse(null)).getSaleName();
                    map.put("saleName", saleName);
                }else {
                    map.put("saleName", "無");
                }

            }
        }
        return appointments;
    }

    public List<SalonSchedule> joinSchedule(List<SalonSchedule> schedules){
        for (SalonSchedule salonSchedule : schedules){
            // groomer
            salonSchedule.setGroomer(staffRepository.findById(salonSchedule.getGroomerId()).orElse(null));
            // asst1
            salonSchedule.setAsst1(staffRepository.findById(salonSchedule.getAsst1Id()).orElse(null));
            // asst2
            salonSchedule.setAsst2(staffRepository.findById(salonSchedule.getAsst2Id()).orElse(null));
            // appointment
            salonSchedule.setSalonAppointment(salonAppointmentRepository.findBySchId(salonSchedule.getId()));
        }
        return schedules;
    }
}
