package com.ipet.core.entities.sub_entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 11:02
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RefEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5237513029398565352L;
    @Field(value = "REF_ID", targetType = FieldType.OBJECT_ID)
    private String id;
}
