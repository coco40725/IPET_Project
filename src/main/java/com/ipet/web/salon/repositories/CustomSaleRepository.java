package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonSale;

/**
 * @author Yu-Jing
 * @create 2023/2/3 上午 09:45
 */
public interface CustomSaleRepository {
    void partialUpdate(SalonSale salonSale);
}
