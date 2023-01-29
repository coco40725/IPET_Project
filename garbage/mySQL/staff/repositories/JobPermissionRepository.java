package com.ipet.web.staff.repositories;

import com.ipet.web.staff.entities.JobPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 12:57
 */
@Repository
public interface JobPermissionRepository extends JpaRepository<JobPermission, Integer> {
}
