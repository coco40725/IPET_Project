package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonSchedule;
import com.ipet.web.salon.entities.unwinded.UnwindedSalonSchedule;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 10:33
 */
public interface CustomSalonScheduleRepository {
    List<UnwindedSalonSchedule>  findAll();
    UnwindedSalonSchedule findById(String id);
    void partialUpdate(SalonSchedule schedule);


}
