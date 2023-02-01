package com.ipet.web.staff.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.staff.entities.Staff;
import com.ipet.web.staff.services.StaffServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-25-上午 09:02
 */
@Controller
public class StaffController {

    private StaffServices staffServices;
    private GsonBuilder builder;

    @Autowired
    public void setStaffServices(StaffServices staffServices){
         this.staffServices = staffServices;
    }

    @Autowired
    public void setBuilder(GsonBuilder builder){
        this.builder = builder;
    }

    //staff add
    //staff delete
    //staff edit
    //staff query
    @PreAuthorize("hasAnyAuthority('displayEmps','editEmps')")
    @GetMapping("/ipet-back/staffs")
    public String getAllStaffs(Model model){
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        model.addAttribute("staffs", staffServices.getAllUnwindedStaff());
        return "backstage/staff/StaffList";
    }

    @PreAuthorize("hasAnyAuthority('displayEmps','editEmps', 'editSalonSchedules')")
    @GetMapping("/ipet-back/staffs/{posi}")
    @ResponseBody
    public String getStaffsByPosi(@PathVariable("posi") String posi){
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(staffServices.getAllStaffByPosi(posi));
    }

}
