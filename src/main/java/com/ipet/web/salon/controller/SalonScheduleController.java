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

import java.sql.Date;
import java.util.*;

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
//        List<SalonSchedule> salonSchedules = new ArrayList<>();
//        String groomerID = map.get("groomerID");
//        String asstID1 = map.get("asstID1");
//        String asstID2 = map.get("asstID2");
//        String notes = map.get("notes");
//
//        for(String date : map.get("dates").split(", ")){
//            for(String period : map.get("periods").split(",")){
//                SalonSchedule salonSchedule = new SalonSchedule();
//                salonSchedule.setGroomer(groomerID);
//                salonSchedule.setAsstId1(asstID1);
//                salonSchedule.setAsstId2(asstID2);
//                salonSchedule.setEmployeeNote(notes);
//                salonSchedule.setSchDate(Date.valueOf(date));
//                salonSchedule.setSchPeriod(period);
//                salonSchedules.add(salonSchedule);
//            }
//        }
//        return sa.addSchedule(salonSchedules);
        return null;
    }

    // schedule edit
    @PutMapping("/ipet-back/schedule")
    @ResponseBody
    public String editSchedule(@RequestBody Map<String, String> map){
        SalonSchedule salonSchedule = new SalonSchedule();

        salonSchedule.setId(map.get("schID"));
        salonSchedule.setSchDate(Date.valueOf(map.get("schDate")));
        salonSchedule.setSchPeriod(map.get("schPeriod"));
//        salonSchedule.setGroomer(Integer.parseInt(map.get("groomerID")));
//        salonSchedule.setAsst1(Integer.parseInt(map.get("asstID1")));
//        salonSchedule.setAsst2(Integer.parseInt(map.get("asstID2")));
        salonSchedule.setEmployeeNote(map.get("employeeNote"));
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
        model.addAttribute("schedules", salonScheduleServices.getAllSchedule());
        return "backstage/salon/salonJobList";
    }

    @GetMapping("/ipet-back/schedules/{id}")
    @ResponseBody
    public String getScheduleById(@PathVariable("id") String id){
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(salonScheduleServices.getScheduleById(id));
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
        model.addAttribute("schedules", salonScheduleServices.getAllSchedule());
        model.addAttribute("groomers", staffServices.findAllStaffByPosi("美容師"));
        model.addAttribute("assts", staffServices.findAllStaffByPosi("美容助理"));
        return "backstage/salon/salonJobCalendar";
    }
}
