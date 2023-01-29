package com.ipet.web.salon.utils;


import com.ipet.web.salon.entities.SalonAppointment;
import com.ipet.web.salon.entities.SalonSchedule;
import com.ipet.web.salon.entities.SalonService;
import com.ipet.web.salon.repositories.SalonAppointmentRepository;
import com.ipet.web.salon.repositories.SalonSaleRepository;
import com.ipet.web.salon.repositories.SalonServiceRepository;
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
    private final SalonAppointmentRepository salonAppointmentRepository;

    public SalonUtils(SalonAppointmentRepository salonAppointmentRepository) {
        this.salonAppointmentRepository = salonAppointmentRepository;
    }

    @Autowired
    public void setSalonServiceRepository(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }
    @Autowired
    public void setSalonSaleRepository(SalonSaleRepository salonSaleRepository) {
        this.salonSaleRepository = salonSaleRepository;
    }


    public List<SalonAppointment> joinAppointInfo(List<SalonAppointment> appointments){
        for (SalonAppointment appointment : appointments){
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
            System.out.println("21212");
            System.out.println(salonAppointmentRepository.findBySchId("63d2bbef34af993e06328d3a"));
            salonSchedule.setSalonAppointment(salonAppointmentRepository.findBySchId(salonSchedule.getId()));
        }
        return schedules;
    }
}
