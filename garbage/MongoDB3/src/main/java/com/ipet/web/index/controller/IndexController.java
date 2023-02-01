package com.ipet.web.index.controller;


import com.ipet.web.staff.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 10:36
 */

@Controller
public class IndexController {
    private StaffRepository staffRepository;
    @Autowired
    public void setStaffRepository(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }

    @Secured({"memberAdmin","salonEmp","salonAdmin","humanAdmin","boss"})
    @RequestMapping("/ipet-back/home")
    public String ipetBackHome(){
        return "backstage/back-index";
    }
}
