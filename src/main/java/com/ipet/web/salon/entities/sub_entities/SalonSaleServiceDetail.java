package com.ipet.web.salon.entities.sub_entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yu-Jing
 * @create 2023-01-29-下午 05:58
 */

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SalonSaleServiceDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = -1763107515969547550L;
    @Field(name = "REF_ID", targetType = FieldType.OBJECT_ID)
    private String id;
    @Field(name = "SALE_PRICE")
    private Integer salePrice;
}
