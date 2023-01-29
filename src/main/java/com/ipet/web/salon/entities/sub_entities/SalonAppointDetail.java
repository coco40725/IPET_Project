package com.ipet.web.salon.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 11:04
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SalonAppointDetail {
    @Field(name = "SVC_ID", targetType = FieldType.OBJECT_ID)
    private String svcId;
    @Field("SVC_PRICE")
    private Integer svcPrice;
    @Field(name = "SALE_ID", targetType = FieldType.OBJECT_ID)
    private String saleId;
    @Field("SALE_PRICE")
    private Integer salePrice;
}
