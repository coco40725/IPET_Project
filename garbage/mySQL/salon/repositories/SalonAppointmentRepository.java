package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:01
 */
@Repository
public interface SalonAppointmentRepository extends JpaRepository<SalonAppointment, Integer> {
    SalonAppointment findBySchId(Integer id);
    List<SalonAppointment> findByApmStatus(Integer status);
}
