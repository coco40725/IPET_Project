package com.ipet.web.staff.controller;

import com.ipet.web.staff.services.JobRoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu-Jing
 * @create 2023-02-01-下午 08:00
 */
@Controller
public class JobRoleController {
    private JobRoleServices jobRoleServices;

    @Autowired
    public void setJobRoleServices(JobRoleServices jobRoleServices){
        this.jobRoleServices = jobRoleServices;
    }
    // add role
    // delete role

    // edit role

    // query role
    @GetMapping("/ipet-back/jobroles")
    public String getAllJobRoles(Model model){
        model.addAttribute("jobroles", jobRoleServices.getAllUnwindedJobRole());
        return "backstage/staff/JobRoleList";
    }
}
