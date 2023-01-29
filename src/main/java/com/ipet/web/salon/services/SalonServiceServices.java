package com.ipet.web.salon.services;

import com.ipet.web.salon.entities.SalonService;
import com.ipet.web.salon.entities.SalonServiceCategory;
import com.ipet.web.salon.repositories.SalonServiceCategoryRepository;
import com.ipet.web.salon.repositories.SalonServiceRepository;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

/**
 * @author Yu-Jing
 * @create 2023-01-26-上午 01:03
 */
@Service
public class SalonServiceServices {
    private SalonServiceCategoryRepository salonServiceCategoryRepository;
    private SalonServiceRepository salonServiceRepository;

    @Autowired
    public void setSalonServiceCategoryRepository(SalonServiceCategoryRepository salonServiceCategoryRepository) {
        this.salonServiceCategoryRepository = salonServiceCategoryRepository;
    }
    @Autowired
    public void setSalonServiceRepository(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }

    // add service
    // delete service
    // edit service


    // query service
    public List<SalonServiceCategory> getAllServicesCategory(){
        // 將圖片編碼成 base64
        List<SalonServiceCategory> all = salonServiceCategoryRepository.findAll();
        byte[] data = null;
        for (SalonServiceCategory serviceCategory : all){
            try (InputStream is = new FileInputStream(serviceCategory.getSvcCategoryImg())){
                data = new byte[is.available()];
                is.read(data);
                serviceCategory.setBase64Img(Base64.getEncoder().encodeToString(IOUtils.toByteArray(is)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return all;
    }

    public List<SalonService> getAllServicesQuery(){
        return salonServiceRepository.findAll();
    }

    public SalonServiceCategory getServicesCategoryById(String id){
        // 將圖片編碼成 base64
        SalonServiceCategory serviceCategory = salonServiceCategoryRepository.findById(id).orElse(null);
        byte[] data = null;
        try (InputStream is = new FileInputStream(serviceCategory.getSvcCategoryImg())){
            data = new byte[is.available()];
            is.read(data);
            serviceCategory.setBase64Img(Base64.getEncoder().encodeToString(IOUtils.toByteArray(is)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return salonServiceCategoryRepository.findById(id).orElse(null);
    }
}
