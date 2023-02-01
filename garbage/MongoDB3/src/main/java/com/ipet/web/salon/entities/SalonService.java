package com.ipet.web.salon.entities;

import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serial;

@EqualsAndHashCode(callSuper = false)
@Data
@Document("SALON_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class SalonService extends Core {

  @Serial
  private static final long serialVersionUID = -2418315317532111138L;
  @Id
  @NonNull
  private String id;
  @Field(name = "SVC_CATEGORY_ID", targetType = FieldType.OBJECT_ID)
  @NonNull
  private String svcCategoryId;
  @Field(name = "SALON_SERVICE_PET_TYPE_ID", targetType = FieldType.OBJECT_ID)
  private String salonServicePetTypeId;
  @Field("SVC_CONTENT")
  @NonNull
  private String svcContent;
  @Field("SVC_NAME")
  @NonNull
  private String svcName;
  @Field("SVC_PRICE")
  @NonNull
  private Integer svcPrice;
  @Field("SVC_STATUS")
  @NonNull
  private Integer svcStatus;
  @Field("TYPE_NAME")
  @NonNull
  private String typeName;
}
