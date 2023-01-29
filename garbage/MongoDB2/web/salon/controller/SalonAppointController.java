package com.ipet.web.salon.controller;

import com.google.gson.*;
import com.ipet.web.salon.entities.SalonAppointment;
import com.ipet.web.salon.services.SalonAppointServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 11:17
 */
@Controller
public class SalonAppointController {
    private SalonAppointServices salonAppointServices;
    private GsonBuilder builder;
    @Autowired
    public void setSalonAppointServices(SalonAppointServices salonAppointServices){
        this.salonAppointServices = salonAppointServices;
    }

    @Autowired
    public void setBuilder(GsonBuilder builder){
        this.builder = builder;
    }

    // Appointment add
    @PostMapping("/ipet-back/appoint")
    public String addAppoint(SalonAppointment appointment){
        return  null;
    }

    // Appointment edit
    @PutMapping("/ipet-back/appoint")
    @ResponseBody
    public String editAppoint(@RequestBody Map<String, String> map){
        Gson gson = builder.serializeNulls().create();
        System.out.println(map.get("jsonFile"));
        SalonAppointment appointment = gson.fromJson(map.get("jsonFile"), SalonAppointment.class);
        return salonAppointServices.editAppointment(appointment);
    }

    // Appointment query
    @GetMapping("/ipet-back/appoints")
    public String getAllAppoints(Model model){
        model.addAttribute("appoints", salonAppointServices.getAllAppointment());
        return "backstage/salon/salonAppointAll";
    }

    @GetMapping("/ipet-back/appoints/{status}")
    public String getAppointsByStatus(@PathVariable("status") Integer status, Model model){
        String template = null;
        model.addAttribute("appoints", salonAppointServices.getAppointByApmStatus(status));
        switch (status) {
            case 0 :
                return  "backstage/salon/salonAppointPayed";
            case 1 :
                return "backstage/salon/salonAppointFinished";
            case 2 :
                return "backstage/salon/salonAppointCancelled";
            case 3:
                return "backstage/salon/salonAppointOutdated";
            default:
                return "/backstage/back-index";
        }
    }

    @GetMapping("/ipet-back/appoint/{id}")
    @ResponseBody
    public String  getAppointById(@PathVariable("id") String id){
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(salonAppointServices.getAppointmentById(id));
    }
}
