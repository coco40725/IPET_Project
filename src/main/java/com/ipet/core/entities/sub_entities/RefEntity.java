package com.ipet.core.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 11:02
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RefEntity {
    @Field(value = "REF_ID", targetType = FieldType.OBJECT_ID)
    private String id;
}
