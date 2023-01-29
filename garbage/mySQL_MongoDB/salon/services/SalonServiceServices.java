package temp.salon.services;

import com.ipet.web.salon.entities.SalonServiceCategory;
import com.ipet.web.salon.entities.SalonServicePetType;
import com.ipet.web.salon.entities.SalonServiceQuery;
import com.ipet.web.salon.repositories.SalonServiceCategoryRepository;
import com.ipet.web.salon.repositories.SalonServicePetTypeRepository;
import com.ipet.web.salon.repositories.SalonServiceQueryRepository;
import com.ipet.web.salon.repositories.SalonServiceRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SalonServiceQueryRepository salonServiceQueryRepository;

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
    public void setSalonServiceQueryRepository(SalonServiceQueryRepository salonServiceQueryRepository) {
        this.salonServiceQueryRepository = salonServiceQueryRepository;
    }

    // add service
    // delete service
    // edit service


    // query service
    public List<SalonServiceCategory> getAllServicesCategory(){
        // 將圖片編碼成 base64
        List<SalonServiceCategory> all = salonServiceCategoryRepository.findAll();
        for (SalonServiceCategory serviceCategory : all){
            Byte[] img = serviceCategory.getSvcCategoryImg();
            if (img != null && img.length != 0){
                serviceCategory.setSvcCategoryImgBase64(Base64.getEncoder().encodeToString(ArrayUtils.toPrimitive(img)));
            }
        }
        return salonServiceCategoryRepository.findAll();
    }

    public List<SalonServicePetType> getAllServicePetType(){
        return salonServicePetTypeRepository.findAll();
    }

    public List<SalonServiceQuery> getAllServicesQuery(){
        return salonServiceQueryRepository.findAll();
    }

    public SalonServiceCategory getServicesCategoryById(Integer id){
        // 將圖片編碼成 base64
        SalonServiceCategory byId = salonServiceCategoryRepository.findById(id).orElse(null);
        Byte[] img = Objects.requireNonNull(byId).getSvcCategoryImg();
        if (img != null && img.length != 0){
            byId.setSvcCategoryImgBase64(Base64.getEncoder().encodeToString(ArrayUtils.toPrimitive(img)));
        }
        return salonServiceCategoryRepository.findById(id).orElse(null);
    }
}
