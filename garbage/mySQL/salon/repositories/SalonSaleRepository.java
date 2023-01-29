package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:02
 */
@Repository
public interface SalonSaleRepository extends JpaRepository<SalonSale, Integer> {
}
