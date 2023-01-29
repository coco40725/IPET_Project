package temp.salon.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.salon.entities.SalonAppointment;
import com.ipet.web.salon.entities.SalonSchedule;
import com.ipet.web.salon.entities.SalonScheduleQuery;
import com.ipet.web.salon.repositories.SalonScheduleQueryRepository;
import com.ipet.web.salon.services.SalonScheduleServices;
import com.ipet.web.staff.entities.Staff;
import com.ipet.web.staff.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Yu-Jing
 * @create 2023-01-24-下午 11:45
 */
@Controller
public class SalonScheduleController {
    private SalonScheduleServices salonScheduleServices;
    private StaffRepository staffRepository;
    private GsonBuilder builder;

    @Autowired
    public void setSalonScheduleServices(SalonScheduleServices salonScheduleServices){
        this.salonScheduleServices = salonScheduleServices;
    }
    @Autowired
    public void setStaffRepository(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
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
        Integer groomerID = Integer.parseInt(map.get("groomerID"));
        Integer asstID1 = Integer.parseInt(map.get("asstID1"));
        Integer asstID2 = Integer.parseInt(map.get("asstID2"));
        String notes = map.get("notes");

        for(String date : map.get("dates").split(", ")){
            for(String period : map.get("periods").split(",")){
                SalonSchedule salonSchedule = new SalonSchedule();
                salonSchedule.setGroomerId(groomerID);
                salonSchedule.setAsstId1(asstID1);
                salonSchedule.setAsstId2(asstID2);
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

        salonSchedule.setSchId(Integer.parseInt(map.get("schID")));
        salonSchedule.setSchDate(Date.valueOf(map.get("schDate")));
        salonSchedule.setSchPeriod(map.get("schPeriod"));
        salonSchedule.setGroomerId(Integer.parseInt(map.get("groomerID")));
        salonSchedule.setAsstId1(Integer.parseInt(map.get("asstID1")));
        salonSchedule.setAsstId2(Integer.parseInt(map.get("asstID2")));
        salonSchedule.setEmployeeNote(map.get("employeeNote"));
        return salonScheduleServices.editSchedule(salonSchedule);
    }

    // schedule delete
    @DeleteMapping("/ipet-back/schedule/{id}")
    @ResponseBody
    public String deleteScheduleById(@PathVariable("id") Integer id){
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
    public String getScheduleById(@PathVariable("id") Integer id){
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

        Date date = new Date(System.currentTimeMillis());
        Integer groomerId = Integer.parseInt(map.get("groomerId"));
        Integer asstId1 = Integer.parseInt(map.get("asstId1"));
        Integer asstId2 = Integer.parseInt(map.get("asstId2"));
        String[] periods = map.get("periods").split(",");
        for (String period : periods){
            illegalDates.addAll(salonScheduleServices.findIllegalDates(date, period, groomerId, asstId1, asstId2));
        }
        return gson.toJson(illegalDates);
    }

    @GetMapping("/ipet-back/schedules/calendar")
    public String getAllScheduleCalendars(Model model){
        model.addAttribute("schedules", salonScheduleServices.getAllSchedule());
        model.addAttribute("groomers", staffRepository.findAllByStaffPosi("美容師"));
        model.addAttribute("assts", staffRepository.findAllByStaffPosi("美容助理"));
        return "backstage/salon/salonJobCalendar";
    }
}
