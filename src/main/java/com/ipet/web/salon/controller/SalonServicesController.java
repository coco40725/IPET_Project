package com.ipet.web.salon.controller;

import com.google.cloud.storage.Storage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.salon.entities.SalonService;
import com.ipet.web.salon.entities.SalonServiceCategory;
import com.ipet.web.salon.services.SalonServiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.PanelUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.util.List;
import java.util.Map;
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
    @PreAuthorize("hasAnyAuthority('editSalonServices')")
    @PostMapping("/ipet-back/category")
    @ResponseBody
    public String addServiceCategory(@RequestParam(name ="imageFile") Optional <MultipartFile> imageFile,
                                     @RequestParam(name ="jsonFile") Optional <MultipartFile> jsonFile){
        Gson gson = builder.serializeNulls().create();
        StringBuilder txtResult = new StringBuilder();
        byte[] image = null;

        try(InputStreamReader isr = new InputStreamReader(jsonFile.get().getInputStream(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr)){
            String lineTxt;
            if (imageFile.isPresent()) {
                image = imageFile.orElse(null).getBytes();
            }
            while((lineTxt = br.readLine()) != null){
                txtResult.append(lineTxt);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        SalonServiceCategory category = gson.fromJson(String.valueOf(txtResult), SalonServiceCategory.class);
        return services.addServiceCategory(category, image);
    }

    @PreAuthorize("hasAnyAuthority('editSalonServices')")
    @PostMapping("/ipet-back/services")
    @ResponseBody
    public String addService(@RequestBody List<SalonService> salonServices){
        return  services.addService(salonServices);
    }

    // delete
    @PreAuthorize("hasAuthority('editSalonServices')")
    @DeleteMapping("/ipet-back/category/{id}")
    @ResponseBody
    public String deleteServiceCategory(@PathVariable("id") String id){
        return  services.deleteServiceCategory(id);
    }

    @PreAuthorize("hasAuthority('editSalonServices')")
    @DeleteMapping("/ipet-back/service/{id}")
    @ResponseBody
    public String deleteService(@PathVariable("id") String id){
        return  services.deleteService(id);
    }


    // edit
    @PreAuthorize("hasAnyAuthority('editSalonServices')")
    @PutMapping("/ipet-back/category/{id}")
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

    @PreAuthorize("hasAuthority('editSalonServices')")
    @PatchMapping("/ipet-back/service/{id}")
    @ResponseBody
    public String editService(@PathVariable("id") String id, @RequestBody SalonService salonService){
        salonService.setId(id);
        return services.editService(salonService);
    }

    // query
    @PreAuthorize("hasAnyAuthority('displaySalonServices','editSalonServices')")
    @GetMapping("/ipet-back/services/categories")
    public String getAllServiceCategoriesWithUnwindedServices(Model model){
        model.addAttribute("services", services.getAllUnwindedServices());
        model.addAttribute("categories", services.getAllServiceCategory());
        model.addAttribute("petTypes", services.getAllServicePetType());
        return "backstage/salon/salonServiceCategory";
    }
    @PreAuthorize("hasAnyAuthority('displaySalonServices','editSalonServices')")
    @GetMapping("/ipet-back/categories")
    @ResponseBody
    public String getAllServiceCategories(){
        Gson gson = builder.serializeNulls().create();
        return gson.toJson(services.getAllServiceCategory());
    }
    @PreAuthorize("hasAnyAuthority('displaySalonServices','editSalonServices')")
    @GetMapping("/ipet-back/category/{id}")
    @ResponseBody
    public String getServiceCategoryById(@PathVariable("id") String id){
        Gson gson = builder.serializeNulls().create();
        return gson.toJson(services.getServiceCategoryById(id));
    }
    @PreAuthorize("hasAnyAuthority('displaySalonServices','editSalonServices')")
    @GetMapping("/ipet-back/services")
    public String getAllUnwindedServices(Model model){
        model.addAttribute("services", services.getAllUnwindedServices());
        model.addAttribute("categories", services.getAllServiceCategory());
        model.addAttribute("petTypes", services.getAllServicePetType());
        return "backstage/salon/salonServiceList";
    }
    @PreAuthorize("hasAnyAuthority('displaySalonServices','editSalonServices')")
    @GetMapping("/ipet-back/service/{id}")
    @ResponseBody
    public String getUnwindedServiceById(@PathVariable("id") String id){
        Gson gson = builder.serializeNulls().create();
        return gson.toJson(services.getUnwindedServicesById(id));
    }
}
