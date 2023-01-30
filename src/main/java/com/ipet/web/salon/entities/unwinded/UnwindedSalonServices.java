package com.ipet.web.salon.entities.unwinded;

import com.ipet.web.salon.entities.SalonServiceCategory;
import com.ipet.web.salon.entities.SalonServicePetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Yu-Jing
 * @create 2023-01-30-下午 03:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UnwindedSalonServices {
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private String id;
    @Field("SERVICE_PET_TYPE")
    private List<SalonServicePetType> salonServicePetType;
    @Field("SVC_CONTENT")
    private String svcContent;
    @Field("SVC_NAME")
    private String svcName;
    @Field("SVC_PRICE")
    private Integer svcPrice;
    @Field("SVC_STATUS")
    private Integer svcStatus;
    @Field("SERVICE_CATEGORY")
    private List<SalonServiceCategory> serviceCategory;
}
