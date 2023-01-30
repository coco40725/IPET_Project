package com.ipet.web.salon.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.salon.entities.SalonSchedule;
import com.ipet.web.salon.services.SalonScheduleServices;
import com.ipet.web.staff.services.StaffServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.sql.Date;

/**
 * @author Yu-Jing
 * @create 2023-01-24-下午 11:45
 */
@Controller
public class SalonScheduleController {
    private StaffServices staffServices;
    private SalonScheduleServices salonScheduleServices;

    private GsonBuilder builder;


    @Autowired
    public void setStaffServices(StaffServices staffServices){
        this.staffServices = staffServices;
    }
    @Autowired
    public void setSalonScheduleServices(SalonScheduleServices salonScheduleServices){
        this.salonScheduleServices = salonScheduleServices;
    }

    @Autowired
    public void setBuilder(GsonBuilder builder){
        this.builder = builder;
    }



    // schedule add
    @PostMapping("/ipet-back/schedule")
    @ResponseBody
    public String addSchedule(@RequestBody Map<String, String> map){
        List<SalonSchedule> salonSchedules = new ArrayList<>();
        String groomerID = map.get("groomer");
        String asstID1 = map.get("asst1");
        String asstID2 = map.get("asst2");
        String notes = map.get("notes");

        for(String date : map.get("dates").split(", ")){
            for(String period : map.get("periods").split(",")){
                SalonSchedule salonSchedule = new SalonSchedule();
                salonSchedule.setGroomerId(groomerID);
                salonSchedule.setAsst1Id(asstID1);
                salonSchedule.setAsst2Id(asstID2);
                salonSchedule.setEmployeeNote(notes);

                salonSchedule.setSchDate(Date.valueOf(date));
                salonSchedule.setSchPeriod(period);
                salonSchedules.add(salonSchedule);
            }
        }
        return salonScheduleServices.addSchedule(salonSchedules);
    }

    // schedule edit
    @PutMapping("/ipet-back/schedule")
    @ResponseBody
    public String editSchedule(@RequestBody Map<String, String> map){
        SalonSchedule salonSchedule = new SalonSchedule();
        String groomerID = map.get("groomerId");
        String asstID1 = map.get("asst1Id");
        String asstID2 = map.get("asst2Id");
        String notes = map.get("employeeNote");
        String id = map.get("id");
        String appointId = salonScheduleServices.getScheduleById(id).getAppointId();
        String period = salonScheduleServices.getScheduleById(id).getSchPeriod();
        java.util.Date date = salonScheduleServices.getScheduleById(id).getSchDate();

        salonSchedule.setId(id);
        salonSchedule.setSchDate(date);
        salonSchedule.setSchPeriod(period);
        salonSchedule.setGroomerId(groomerID);
        salonSchedule.setAsst1Id(asstID1);
        salonSchedule.setAsst2Id(asstID2);
        salonSchedule.setEmployeeNote(notes);

        if (appointId != null){
            salonSchedule.setAppointId(appointId);
        }

        return salonScheduleServices.editSchedule(salonSchedule);
    }

    // schedule delete
    @DeleteMapping("/ipet-back/schedule/{id}")
    @ResponseBody
    public String deleteScheduleById(@PathVariable("id") String id){
        return salonScheduleServices.deleteSchedule(id);
    }


    // schedule query
    @GetMapping("/ipet-back/schedules")
    public String getAllSchedules(Model model){
        model.addAttribute("schedules", salonScheduleServices.getAllUnwindedSchedule());
        return "backstage/salon/salonJobList";
    }

    @GetMapping("/ipet-back/schedules/{id}")
    @ResponseBody
    public String getScheduleById(@PathVariable("id") String id){
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(salonScheduleServices.getUnwindedScheduleById(id));
    }


    @GetMapping("/ipet-back/schedules/available")
    @ResponseBody
    public String getAvailableSchedule(){
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(salonScheduleServices.findAvailableJobsToAddAppoint());
    }

    @PostMapping("/ipet-back/schedules/illegal")
    @ResponseBody
    public String getIllegalScheduleDate(@RequestBody Map<String, String> map){
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        List<Object> illegalDates = new ArrayList<>();

        String groomerId = map.get("groomerId");
        String asstId1 = map.get("asstId1");
        String asstId2 = map.get("asstId2");
        String[] periods = map.get("periods").split(",");
        for (String period : periods){
            illegalDates.addAll(salonScheduleServices.findIllegalDates(period, groomerId, asstId1, asstId2));
        }
        return gson.toJson(illegalDates);
    }

    @GetMapping("/ipet-back/schedules/calendar")
    public String getAllScheduleCalendars(Model model){
        model.addAttribute("schedules", salonScheduleServices.getAllUnwindedSchedule());
        model.addAttribute("groomers", staffServices.getAllStaffByPosi("美容師"));
        model.addAttribute("assts", staffServices.getAllStaffByPosi("美容助理"));
        return "backstage/salon/salonJobCalendar";
    }
}
