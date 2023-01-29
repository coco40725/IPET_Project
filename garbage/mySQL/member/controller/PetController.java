package com.ipet.web.member.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.member.entities.Pet;
import com.ipet.web.member.services.PetServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    private GsonBuilder builder;

    @Autowired
    public void setPetServices(PetServices petServices){
        this.petServices = petServices;
    }

    @Autowired
    public void setBuilder(GsonBuilder builder){
        this.builder = builder;
    }

    // pet add

    // pet edit
    @PutMapping("/ipet-back/pet")
    @ResponseBody
    public String editPet(@RequestBody Map<String, String> map){
        Gson gson = builder.serializeNulls().create();
        Pet pet = gson.fromJson(map.get("jsonFile"), Pet.class);
        return petServices.editPet(pet);
    }
    // pet query
    @GetMapping("/ipet-back/pets")
    public String getAllPets(Model model){
        model.addAttribute("pets", petServices.getAllPets());
        return "backstage/member/petList";
    }

    @GetMapping("/ipet-back/pet/{id}")
    @ResponseBody
    public String getPetById(@PathVariable("id") Integer id){
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(petServices.getPetById(id));
    }
}
