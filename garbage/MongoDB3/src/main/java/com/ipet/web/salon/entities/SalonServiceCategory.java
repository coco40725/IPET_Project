package com.ipet.web.salon.entities;


import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;

@EqualsAndHashCode(callSuper = false)
@Data
@Document("SALON_SERVICE_CATEGORY")
@AllArgsConstructor
@NoArgsConstructor
public class SalonServiceCategory extends Core {

  @Serial
  private static final long serialVersionUID = -6738708137728091403L;
  @Id
  @NonNull
  private String id;
  @Field("SVC_CATEGORY_IMG")
  private String svcCategoryImg;
  @Field("SVC_CATEGORY_NAME")
  @NonNull
  private String svcCategoryName;
  @Field("SVC_CATEGORY_STATUS")
  @NonNull
  private Integer svcCategoryStatus;
  @Transient
  private String svcCategoryImgSignedUrl;
}
