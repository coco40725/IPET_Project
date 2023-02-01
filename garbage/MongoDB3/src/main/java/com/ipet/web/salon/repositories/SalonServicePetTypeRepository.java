package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonServicePetType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-30-下午 05:05
 */
@Repository
public interface SalonServicePetTypeRepository extends MongoRepository<SalonServicePetType, String> {
}
