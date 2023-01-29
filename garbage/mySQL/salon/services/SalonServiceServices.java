package com.ipet.web.salon.services;

import com.ipet.web.salon.entities.SalonService;
import com.ipet.web.salon.entities.SalonServiceCategory;
import com.ipet.web.salon.entities.SalonServicePetType;
import com.ipet.web.salon.repositories.SalonServiceCategoryRepository;
import com.ipet.web.salon.repositories.SalonServicePetTypeRepository;
import com.ipet.web.salon.repositories.SalonServiceRepository;
import com.ipet.web.salon.utils.SalonUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private SalonServicePetTypeRepository salonServicePetTypeRepository;
    private SalonServiceRepository salonServiceRepository;
    private SalonUtils salonUtils;

    @Autowired
    public void setSalonServiceCategoryRepository(SalonServiceCategoryRepository salonServiceCategoryRepository) {
        this.salonServiceCategoryRepository = salonServiceCategoryRepository;
    }
    @Autowired
    public void setSalonServicePetTypeRepository(SalonServicePetTypeRepository salonServicePetTypeRepository) {
        this.salonServicePetTypeRepository = salonServicePetTypeRepository;
    }
    @Autowired
    public void setSalonServiceRepository(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }

    @Autowired
    public void setSalonUtils(SalonUtils salonUtils){
        this.salonUtils = salonUtils;
    }

    // add service
    // delete service
    // edit service


    // query service
    public List<SalonServiceCategory> getAllServicesCategory(){
        return salonUtils.joinServiceCategory(salonServiceCategoryRepository.findAll());
    }
    public List<SalonServicePetType> getAllServicePetType(){
        return salonServicePetTypeRepository.findAll();
    }
    public SalonServiceCategory getServicesCategoryById(Integer id){
        List<SalonServiceCategory> list = new ArrayList<>();
        list.add(salonServiceCategoryRepository.findById(id).orElse(null));
        return salonUtils.joinServiceCategory(list).get(0);
    }
    public List<SalonService> getAllServices(){
        return salonUtils.joinService(salonServiceRepository.findAll());
    }
}
