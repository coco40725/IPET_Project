package com.ipet.web.member.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.member.entities.Pet;
import com.ipet.web.member.services.MemberServices;
import com.ipet.web.member.services.PetServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Yu-Jing
 * @create 2023-01-25-下午 10:32
 */
@Controller
public class PetController {
    private PetServices petServices;
    private MemberServices memberServices;
    private GsonBuilder builder;

    @Autowired
    public void setPetServices(PetServices petServices){
        this.petServices = petServices;
    }

    @Autowired
    public void setBuilder(GsonBuilder builder){
        this.builder = builder;
    }
    @Autowired
    public void setMemberServices(MemberServices memberServices){
        this.memberServices = memberServices;
    }

    // pet add

    // pet edit
    @PreAuthorize("hasAnyAuthority('editMems')")
    @PutMapping("/ipet-back/pet")
    @ResponseBody
    public String editPet(@RequestBody Map<String, String> map){
        Gson gson = builder.serializeNulls().create();
        Pet pet = gson.fromJson(map.get("jsonFile"), Pet.class);
        return petServices.editPet(pet);
    }

    // pet query
    @PreAuthorize("hasAnyAuthority('editMems','displayMems')")
    @GetMapping("/ipet-back/pets")
    public String getAllPets(Model model){
        model.addAttribute("members", memberServices.getAllUnwindedMembers());
        return "backstage/member/petList";
    }

    @PreAuthorize("hasAnyAuthority('editMems','displayMems')")
    @GetMapping("/ipet-back/pet/{id}")
    @ResponseBody
    public String getPetById(@PathVariable("id") String id){
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(petServices.getPetById(id));
    }
}
