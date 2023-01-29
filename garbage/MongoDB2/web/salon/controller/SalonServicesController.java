package com.ipet.web.salon.controller;

import com.ipet.web.salon.services.SalonScheduleServices;
import com.ipet.web.salon.services.SalonServiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu-Jing
 * @create 2023-01-28-下午 04:24
 */
@Controller
public class SalonServicesController {
    private SalonServiceServices services;

    @Autowired
    public void setServices(SalonServiceServices services){
        this.services = services;
    }
    // add
    // delete
    // edit
    // query
    @GetMapping("/ipet-back/services/categories")
    public String getAllServiceCategories(Model model){
        model.addAttribute("categories", services.getAllServicesCategory());
        return "backstage/salon/salonServiceCategory";
    }
}
