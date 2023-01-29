package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonSchedule;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 10:33
 */
public interface CustomSalonScheduleRepository {
    List<SalonSchedule>  findAll();
    SalonSchedule  findById(String id);

}
