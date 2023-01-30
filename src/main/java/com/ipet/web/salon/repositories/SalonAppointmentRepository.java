package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonAppointment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-27-下午 05:08
 */
@Repository
public interface SalonAppointmentRepository extends MongoRepository<SalonAppointment, String> {
    List<SalonAppointment> findByApmStatus(Integer apmStatus);
    SalonAppointment findBySchId(String id);
}
