package com.ipet.web.salon.entities;

import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;

/**
 * @author Yu-Jing
 * @create 2023-01-30-下午 04:56
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("SALON_SERVICE_PET_TYPE")
public class SalonServicePetType extends Core {
    @Serial
    private static final long serialVersionUID = -2251479346369349236L;
    @Id
    @NonNull
    private String id;
    @Field("PET_SIZE")
    @NonNull
    private String petSize;
    @Field("TYPE_NAME")
    @NonNull
    private String typeName;

}
