package com.ipet.web.salon.entities;

import com.ipet.core.entities.Core;
import com.ipet.web.salon.entities.sub_entities.SalonAppointDetail;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Document("SALON_APPOINTMENT")
@AllArgsConstructor
@NoArgsConstructor
public class SalonAppointment extends Core {

  @Serial
  private static final long serialVersionUID = 1269939229608820956L;
  @Id
  @NonNull
  private String id;
  @Field("APM_BUILD_TIME")
  @NonNull
  private Date apmBuildTime;
  @Field("APM_STATUS")
  @NonNull
  private Integer apmStatus;
  @Field("CUSTOMER_NOTE")
  private String customerNote;
  @Field(name = "MEM_ID", targetType = FieldType.OBJECT_ID)
  @NonNull
  private String memId;
  @Field(name = "PET_ID", targetType = FieldType.OBJECT_ID)
  @NonNull
  private String petId;
  @Field(name = "SCH_ID", targetType = FieldType.OBJECT_ID)
  @NonNull
  private String schId;
  @Field("SALON_APPOINTMENT_DETAIL")
  @NonNull
  private List<SalonAppointDetail> salonAppointDetail;
  @Field("TOTAL_PRICE")
  @NonNull
  private Integer totalPrice;
}
