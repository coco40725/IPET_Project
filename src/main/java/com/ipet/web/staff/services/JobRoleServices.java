package com.ipet.web.staff.services;

import com.ipet.web.staff.entities.unwinded.UnwindedJobRole;
import com.ipet.web.staff.repositories.CustomJobRoleRepository;
import com.ipet.web.staff.repositories.JobRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-02-01-下午 08:08
 */
@Service
public class JobRoleServices {
    private CustomJobRoleRepository customJobRoleRepository;
    private JobRoleRepository jobRoleRepository;

    @Autowired
    public void setCustomJobRoleRepository(CustomJobRoleRepository customJobRoleRepository){
        this.customJobRoleRepository = customJobRoleRepository;
    }
    @Autowired
    public void setJobRoleRepository(JobRoleRepository jobRoleRepository){
        this.jobRoleRepository = jobRoleRepository;
    }

    // add role
    // delete role
    // edit role
    // query role
    public List<UnwindedJobRole> getAllUnwindedJobRole(){
        return customJobRoleRepository.findAll();
    }
}
