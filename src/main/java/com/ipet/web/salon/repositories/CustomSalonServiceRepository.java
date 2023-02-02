package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonService;
import com.ipet.web.salon.entities.SalonServiceCategory;
import com.ipet.web.salon.entities.unwinded.UnwindedSalonServices;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-30-下午 03:18
 */
public interface CustomSalonServiceRepository {
    List<UnwindedSalonServices> findAll();
    UnwindedSalonServices findById(String id);
    void partialUpdate(SalonService salonService);
}
