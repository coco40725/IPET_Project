package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonServiceCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-27-下午 05:09
 */
@Repository
public interface SalonServiceCategoryRepository extends MongoRepository<SalonServiceCategory, String> {
}
