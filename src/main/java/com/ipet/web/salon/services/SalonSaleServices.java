package com.ipet.web.salon.services;

import com.ipet.web.salon.entities.SalonSale;
import com.ipet.web.salon.entities.SalonService;
import com.ipet.web.salon.entities.sub_entities.SalonSaleServiceDetail;
import com.ipet.web.salon.entities.unwinded.UnwindedSalonServices;
import com.ipet.web.salon.repositories.CustomSaleRepository;
import com.ipet.web.salon.repositories.CustomSalonServiceRepository;
import com.ipet.web.salon.repositories.SalonSaleRepository;
import com.ipet.web.salon.repositories.SalonServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.TimeZone;

/**
 * @author Yu-Jing
 * @create 2023-01-23-下午 09:38
 */
@Service
public class SalonSaleServices {
    private SalonSaleRepository salonSaleRepository;
    private CustomSaleRepository customSaleRepository;
    private CustomSalonServiceRepository customSalonServiceRepository;


    @Autowired
    public void setSalonSaleRepository(SalonSaleRepository salonSaleRepository){
        this.salonSaleRepository = salonSaleRepository;
    }
    @Autowired
    public void setCustomSaleRepository(CustomSaleRepository customSaleRepository){
        this.customSaleRepository = customSaleRepository;
    }

    @Autowired
    public void setCustomSalonServiceRepository(CustomSalonServiceRepository customSalonServiceRepository){
        this.customSalonServiceRepository = customSalonServiceRepository;
    }
    // sale add
    @Transactional
    public String addSalonSale(SalonSale salonSale){
        // 將SalonSaleServiceDetail
        List<SalonSaleServiceDetail> svcDetailIds = salonSale.getSvcDetailIds();
        Integer saleStrategy = salonSale.getSaleStrategy();
        // 若 svcDetailIds == null 則代表完全不用改，
        // svcDetailIds == [] 代表全數移除，
        // 其餘都是有更改並且要補上資料;
        if (svcDetailIds == null || svcDetailIds.size() == 0){
            salonSaleRepository.save(salonSale);

        } else {
            for (SalonSaleServiceDetail svcDetailId : svcDetailIds) {
                UnwindedSalonServices salonService = customSalonServiceRepository.findById(svcDetailId.getId());
                svcDetailId.setSvcName(salonService.getSvcName());
                svcDetailId.setSvcPrice(salonService.getSvcPrice());
                svcDetailId.setPetId(salonService.getSalonServicePetType().get(0).getId());
                svcDetailId.setPetSize(salonService.getSalonServicePetType().get(0).getPetSize());
                svcDetailId.setPetType(salonService.getSalonServicePetType().get(0).getTypeName());
                // 0 是打折
                if (saleStrategy == 0){
                    svcDetailId.setSalePrice((int) Math.round(svcDetailId.getSalePrice() * svcDetailId.getSvcPrice() * 0.1));
                    // 1是折扣
                }else if (saleStrategy == 1){
                    svcDetailId.setSalePrice(svcDetailId.getSvcPrice() - svcDetailId.getSalePrice());
                }else{
                    // 沒有改
                    svcDetailId.setSalePrice(svcDetailId.getSalePrice());
                }
            }
            salonSaleRepository.save(salonSale);
        }
        return "success";
    }

    // sale edit
    @Transactional
    public String editSalonSale(SalonSale salonSale){

        // 將SalonSaleServiceDetail
        List<SalonSaleServiceDetail> svcDetailIds = salonSale.getSvcDetailIds();
        Integer saleStrategy = salonSale.getSaleStrategy();
        // 若 svcDetailIds == null 則代表完全不用改，
        // svcDetailIds == [] 代表全數移除，
        // 其餘都是有更改並且要補上資料;
        if (svcDetailIds == null || svcDetailIds.size() == 0){
            customSaleRepository.partialUpdate(salonSale);

        } else {
            for (SalonSaleServiceDetail svcDetailId : svcDetailIds) {
                UnwindedSalonServices salonService = customSalonServiceRepository.findById(svcDetailId.getId());
                svcDetailId.setSvcName(salonService.getSvcName());
                svcDetailId.setSvcPrice(salonService.getSvcPrice());
                svcDetailId.setPetId(salonService.getSalonServicePetType().get(0).getId());
                svcDetailId.setPetSize(salonService.getSalonServicePetType().get(0).getPetSize());
                svcDetailId.setPetType(salonService.getSalonServicePetType().get(0).getTypeName());
                // 0 是打折
                if (saleStrategy == 0){
                    svcDetailId.setSalePrice((int) Math.round(svcDetailId.getSalePrice() * svcDetailId.getSvcPrice() * 0.1));
                    // 1是折扣
                }else if (saleStrategy == 1){
                    svcDetailId.setSalePrice(svcDetailId.getSvcPrice() - svcDetailId.getSalePrice());
                }else{
                    // 沒有改
                    svcDetailId.setSalePrice(svcDetailId.getSalePrice());
                }
            }
            customSaleRepository.partialUpdate(salonSale);
        }
        return "success";
    }

    @Transactional
    // sale delete
    public String deleteSalonSale(String id){
        if (salonSaleRepository.findById(id).orElse(null) == null){
            return "查無此優惠";
        }
        salonSaleRepository.deleteById(id);
        return "success";
    }

    // sale query
    public List<SalonSale> getAllSalonSales(){
        return salonSaleRepository.findAll();
    }
    public SalonSale getSalonSaleById(String id){
        return salonSaleRepository.findById(id).orElse(null);
    }
}
