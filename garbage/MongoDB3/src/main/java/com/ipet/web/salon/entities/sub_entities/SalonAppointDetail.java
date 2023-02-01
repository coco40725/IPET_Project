package com.ipet.web.salon.entities.sub_entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 11:04
 */

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SalonAppointDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 5161530834160243224L;
    @Field(name = "SVC_ID", targetType = FieldType.OBJECT_ID)
    private String svcId;
    @Field(name = "SVC_CATEGORY_ID", targetType = FieldType.OBJECT_ID)
    private String svcCategoryId;
    @Field(name = "SVC_CATEGORY_NAME")
    private String svcCategoryName;
    @Field("TYPE_NAME")
    private String typeName;
    @Field("PET_SIZE")
    private String petSize;
    @Field(name = "SVC_NAME")
    private String svcName;
    @Field("SVC_CONTENT")
    private String svcContent;
    @Field("SVC_PRICE")
    private Integer svcPrice;
    @Field(name = "SALE_ID", targetType = FieldType.OBJECT_ID)
    private String saleId;
    @Field("SALE_NAME")
    private String saleName;
    @Field("SALE_PRICE")
    private Integer salePrice;
}
