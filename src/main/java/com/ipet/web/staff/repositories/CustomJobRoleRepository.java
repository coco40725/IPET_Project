package com.ipet.web.staff.repositories;

import com.ipet.web.staff.entities.unwinded.UnwindedJobRole;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-02-01-下午 08:01
 */
public interface CustomJobRoleRepository {
    List<UnwindedJobRole> findAll();
    UnwindedJobRole findById(String id);
}
