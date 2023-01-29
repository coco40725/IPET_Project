package com.ipet.web.staff.repositories;

import com.ipet.web.staff.entities.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-27-下午 05:13
 */
@Repository
public interface StaffRepository extends MongoRepository<Staff, String> {
    List<Staff> findAllByStaffPosi(String posi);
}
