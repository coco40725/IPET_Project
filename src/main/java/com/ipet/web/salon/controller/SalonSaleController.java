package com.ipet.web.salon.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.salon.entities.SalonSale;
import com.ipet.web.salon.services.SalonSaleServices;
import com.ipet.web.salon.services.SalonServiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yu-Jing
 * @create 2023/2/3 上午 09:34
 */
@Controller
public class SalonSaleController {
    private SalonSaleServices salonSaleServices;
    private SalonServiceServices services;
    private GsonBuilder builder;
    @Autowired
    public void setSalonService(SalonSaleServices salonSaleServices){
        this.salonSaleServices = salonSaleServices;
    }
    @Autowired
    public void setServices(SalonServiceServices services){
        this.services = services;
    }

    @Autowired
    public void setBuilder(GsonBuilder builder){
        this.builder = builder;
    }
    // add sale
    @PreAuthorize("hasAnyAuthority('displaySalonSales', 'editSalonSales')")
    @PatchMapping("/ipet-back/sale")
    @ResponseBody
    public String addSale(@RequestBody SalonSale salonSale){
        return salonSaleServices.addSalonSale(salonSale);
    }

    // delete sale
    @PreAuthorize("hasAnyAuthority('displaySalonSales', 'editSalonSales')")
    @DeleteMapping("/ipet-back/sale/{id}")
    @ResponseBody
    public String deleteSale(@PathVariable("id") String id){
        return salonSaleServices.deleteSalonSale(id);
    }

    // edit sale
    @PreAuthorize("hasAnyAuthority('displaySalonSales', 'editSalonSales')")
    @PatchMapping("/ipet-back/sale/{id}")
    @ResponseBody
    public String editSale(@PathVariable("id") String id, @RequestBody SalonSale salonSale){
        salonSale.setId(id);
        return salonSaleServices.editSalonSale(salonSale);
    }
    // query sale
    @PreAuthorize("hasAnyAuthority('displaySalonSales', 'editSalonSales')")
    @GetMapping("/ipet-back/sales")
    public String getAllSales(Model model){
        model.addAttribute("sales", salonSaleServices.getAllSalonSales());
        model.addAttribute("services", services.getAllUnwindedServices());
        model.addAttribute("categories", services.getAllServiceCategory());
        return "backstage/salon/salonSale";
    }

    @PreAuthorize("hasAnyAuthority('displaySalonSales', 'editSalonSales')")
    @GetMapping("/ipet-back/sale/{id}")
    @ResponseBody
    public String getSaleById(@PathVariable("id") String id){
        SalonSale salonSaleById = salonSaleServices.getSalonSaleById(id);
        Gson gson = builder.serializeNulls().setDateFormat("YYYY-MM-dd HH:mm:ss").create();
        return gson.toJson(salonSaleById);
    }
}
