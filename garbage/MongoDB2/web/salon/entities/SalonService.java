package com.ipet.web.salon.entities;

import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
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
  @Field("PET_SIZE")
  @NonNull
  private String petSize;
  @Field("SVC_CATEGORY")
  @NonNull
  private String  svcCategory;
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

  @Transient
  private SalonServiceCategory serviceCategory;

}
