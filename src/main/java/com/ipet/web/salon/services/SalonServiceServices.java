package com.ipet.web.salon.services;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.ipet.core.utils.GenerateV4GetObjectSignedUrl;
import com.ipet.web.salon.entities.SalonService;
import com.ipet.web.salon.entities.SalonServiceCategory;
import com.ipet.web.salon.entities.SalonServicePetType;
import com.ipet.web.salon.entities.unwinded.UnwindedSalonServices;
import com.ipet.web.salon.repositories.CustomSalonServiceRepository;
import com.ipet.web.salon.repositories.SalonServiceCategoryRepository;
import com.ipet.web.salon.repositories.SalonServicePetTypeRepository;
import com.ipet.web.salon.repositories.SalonServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Yu-Jing
 * @create 2023-01-26-上午 01:03
 */
@Service
public class SalonServiceServices {
    private SalonServiceCategoryRepository salonServiceCategoryRepository;
    private SalonServiceRepository salonServiceRepository;
    private SalonServicePetTypeRepository salonServicePetTypeRepository;
    private CustomSalonServiceRepository customSalonServiceRepository;
    private GenerateV4GetObjectSignedUrl generateV4GetObjectSignedUrl;
    private Storage storage;

    @Autowired
    public void setSalonServiceCategoryRepository(SalonServiceCategoryRepository salonServiceCategoryRepository) {
        this.salonServiceCategoryRepository = salonServiceCategoryRepository;
    }
    @Autowired
    public void setSalonServiceRepository(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }
    @Autowired
    public void setCustomSalonServiceRepository(CustomSalonServiceRepository customSalonServiceRepository){
        this.customSalonServiceRepository = customSalonServiceRepository;
    }

    @Autowired
    public void setGenerateV4GetObjectSignedUrl(GenerateV4GetObjectSignedUrl generateV4GetObjectSignedUrl){
        this.generateV4GetObjectSignedUrl = generateV4GetObjectSignedUrl;
    }
    @Autowired
    public void setSalonServicePetTypeRepository(SalonServicePetTypeRepository salonServicePetTypeRepository){
        this.salonServicePetTypeRepository = salonServicePetTypeRepository;
    }

    @Autowired
    public void setStorage(Storage storage){
        this.storage = storage;
    }

    @Value("${gcp.bucketName}")
    private String bucketName;
    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;



    // add service
    @Transactional
    public String addServiceCategory(SalonServiceCategory serviceCategory, byte[] imageFile){
        // inset file first then storage image
        salonServiceCategoryRepository.save(serviceCategory);
        if (imageFile != null && imageFile.length != 0){
            String fileName = "ipet-image/salonCategory/" + serviceCategory.getId() + ".jpg";
            BlobId blobId = BlobId.of(bucketName ,fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            serviceCategory.setSvcCategoryImg(fileName);
            storage.create(blobInfo, imageFile);
            serviceCategory.setId(serviceCategory.getId());
            salonServiceCategoryRepository.save(serviceCategory);
        }
        return "success";
    }

    // delete service
    // edit service
    @Transactional
    public String editServiceCategory(SalonServiceCategory serviceCategory, byte[] imageFile){
        if (imageFile != null && imageFile.length != 0){
            String fileName = "ipet-image/salonCategory/" + serviceCategory.getId() + ".jpg";
            BlobId blobId = BlobId.of(bucketName ,fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            serviceCategory.setSvcCategoryImg(fileName);
            storage.create(blobInfo, imageFile);
        }
        salonServiceCategoryRepository.save(serviceCategory);
        return "success";
    }

    @Transactional
    public String editService(SalonService salonService){
        customSalonServiceRepository.partialUpdate(salonService);
        return  "success";
    }

    // query service
    public List<UnwindedSalonServices> getAllUnwindedServices(){
        return customSalonServiceRepository.findAll();
    }

    public UnwindedSalonServices getUnwindedServicesById(String id){
        return customSalonServiceRepository.findById(id);
    }

    public List<SalonService> getAllServices(){
        return salonServiceRepository.findAll();
    }

    public SalonService getServiceById(String id){
        return salonServiceRepository.findById(id).orElse(null);
    }

    public List<SalonServiceCategory> getAllServiceCategory(){
        // Generate SignedUrl
        List<SalonServiceCategory> all = salonServiceCategoryRepository.findAll();
        for (SalonServiceCategory category : all){
            if (category.getSvcCategoryImg() != null){
                URL url = generateV4GetObjectSignedUrl.generateV4GetObjectSignedUrl(projectId, bucketName, category.getSvcCategoryImg());
                category.setSvcCategoryImgSignedUrl(String.valueOf(url));
            }
        }
        return all;
    }

    public SalonServiceCategory getServiceCategoryById(String id){
        SalonServiceCategory category = salonServiceCategoryRepository.findById(id).orElse(null);
        if (category != null && category.getSvcCategoryImg() != null){
            URL url = generateV4GetObjectSignedUrl.generateV4GetObjectSignedUrl(projectId, bucketName, category.getSvcCategoryImg());
            category.setSvcCategoryImgSignedUrl(String.valueOf(url));
        }
        return category;
    }

    public List<SalonServicePetType> getAllServicePetType(){
        return salonServicePetTypeRepository.findAll();
    }
}
