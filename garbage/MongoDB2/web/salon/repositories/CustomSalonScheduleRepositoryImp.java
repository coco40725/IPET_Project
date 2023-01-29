package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonSchedule;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 10:33
 */
@Repository
public class CustomSalonScheduleRepositoryImp implements CustomSalonScheduleRepository{
    @Override
    public List<SalonSchedule> findAll() {
        return null;
    }

    @Override
    public SalonSchedule findById(String id) {
        return null;
    }
}
