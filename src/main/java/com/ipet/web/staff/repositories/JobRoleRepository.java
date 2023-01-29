package com.ipet.web.staff.repositories;

import com.ipet.web.staff.entities.JobRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-27-下午 05:13
 */
@Repository
public interface JobRoleRepository extends MongoRepository<JobRole, String> {
}
