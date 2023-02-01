package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-27-下午 05:08
 */
@Repository
public interface SalonServiceRepository extends MongoRepository<SalonService, String> {
}
