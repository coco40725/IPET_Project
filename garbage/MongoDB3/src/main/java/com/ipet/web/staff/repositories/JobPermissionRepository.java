package com.ipet.web.staff.repositories;

import com.ipet.web.staff.entities.JobPermission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-27-下午 05:14
 */
@Repository
public interface JobPermissionRepository extends MongoRepository<JobPermission, String> {
}
