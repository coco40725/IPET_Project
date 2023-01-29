package com.ipet.web.salon.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.salon.entities.SalonServiceCategory;
import com.ipet.web.salon.services.SalonSaleServices;
import com.ipet.web.salon.services.SalonServiceServices;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

/**
 * @author Yu-Jing
 * @create 2023-01-26-上午 01:03
 */
@Controller
public class SalonServiceController {
    private SalonServiceServices serviceServices;
    private GsonBuilder builder;

    @Autowired
    public void setSaleServices(SalonServiceServices serviceServices){
        this.serviceServices = serviceServices;
    }
    @Autowired
    public void setBuilder(GsonBuilder builder){
        this.builder = builder;
    }
    // add service
    // delete service
    // edit service
    @PutMapping("/ipet-back/services/category")
    @ResponseBody
    public String editServiceCategory(@RequestBody Map<String, String> map){
        SalonServiceCategory category = new SalonServiceCategory();

        category.setSvcCategoryId(Integer.parseInt(map.get("svcCategoryId")));
        category.setSvcCategoryName(map.get("svcCategoryName"));
        String svcCategoryImg = map.get("svcCategoryImg").split(",")[1];
        Byte[] bytes = ArrayUtils.toObject(Base64.getDecoder().decode(svcCategoryImg));
        category.setSvcCategoryImg(bytes);
        category.setSvcCategoryStatus(Integer.parseInt(map.get("svcCategoryStatus")));


        System.out.println(category);
        return null;
    }
    // query service
    @GetMapping("/ipet-back/services/categories")
    public String getAllServices(Model model){
        model.addAttribute("categories", serviceServices.getAllServicesCategory());
        model.addAttribute("petTypes", serviceServices.getAllServicePetType());
        model.addAttribute("services", serviceServices.getAllServices());
        return "backstage/salon/salonServiceCategory";
    }

    @GetMapping("/ipet-back/services/categories/{id}")
    @ResponseBody
    public String getServiceCategoryById(@PathVariable("id") Integer id){
        Gson gson = builder.serializeNulls().create();
        return gson.toJson(serviceServices.getServicesCategoryById(id));
    }
}
