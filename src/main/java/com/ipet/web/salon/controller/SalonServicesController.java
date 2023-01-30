package com.ipet.web.salon.controller;

import com.google.cloud.storage.Storage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.salon.entities.SalonServiceCategory;
import com.ipet.web.salon.services.SalonServiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.util.Optional;


/**
 * @author Yu-Jing
 * @create 2023-01-28-下午 04:24
 */
@Controller
public class SalonServicesController {
    private SalonServiceServices services;
    private GsonBuilder builder;


    @Autowired
    public void setServices(SalonServiceServices services){
        this.services = services;
    }
    @Autowired
    public void setBuilder(GsonBuilder builder){
        this.builder = builder;
    }


    // add
    // delete
    // edit
    @PutMapping("/ipet-back/services/category")
    @ResponseBody
    public String editServiceCategory(@RequestParam(name = "imageFile") Optional <MultipartFile> imageFile,
                                      @RequestParam(name = "jsonFile") MultipartFile jsonFile){
        Gson gson = builder.serializeNulls().create();
        StringBuilder txtResult = new StringBuilder();
        byte[] image = null;

        // 將 MultipartFile 轉換成  json 格式
        try (InputStreamReader isr = new InputStreamReader(jsonFile.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)){
            String lineTxt;
            if (imageFile.isPresent()) {
                image = imageFile.orElse(null).getBytes();
            }
            while ((lineTxt = br.readLine()) != null) {
                txtResult.append(lineTxt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        SalonServiceCategory category = gson.fromJson(String.valueOf(txtResult), SalonServiceCategory.class);
        return services.editServiceCategory(category, image);
    }

    // query
    @GetMapping("/ipet-back/services/categories")
    public String getAllServiceCategories(Model model){
        model.addAttribute("services", services.getAllUnwindedServices());
        model.addAttribute("categories", services.getAllServiceCategory());
        model.addAttribute("petTypes", services.getAllServicePetType());
        return "backstage/salon/salonServiceCategory";
    }

    @GetMapping("/ipet-back/services/categories/{id}")
    @ResponseBody
    public String getServiceCategoryById(@PathVariable("id") String id){
        Gson gson = builder.serializeNulls().create();
        return gson.toJson(services.getServiceCategoryById(id));
    }
}
