package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonAppointment;
import com.ipet.web.salon.entities.unwinded.UnwindedSalonAppointment;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-下午 03:43
 */
public interface CustomAppointmentRepository {
    List<UnwindedSalonAppointment> findAll();
    List<UnwindedSalonAppointment> findAllByApmStatus(Integer apmStatus);
    UnwindedSalonAppointment findById(String id);
    void partialUpdate(SalonAppointment salonAppointment);


}
