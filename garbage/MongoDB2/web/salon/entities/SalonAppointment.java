package com.ipet.web.salon.entities;

import com.ipet.core.entities.Core;
import com.ipet.web.member.entities.Member;
import com.ipet.web.member.entities.Pet;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.util.Date;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
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
  @Field("MEM_ID")
  @NonNull
  private String memId;
  @Field("PET_ID")
  @NonNull
  private String petId;
  @Field("SCH_ID")
  @NonNull
  private String schId;
  @Field("SALON_APPOINTMENT_DETAIL")
  @NonNull
  private List<Map<String, String>> salonAppointmentDetail;
  @Field("TOTAL_PRICE")
  @NonNull
  private Integer totalPrice;
  @Transient
  private Member mem;
  @Transient
  private Pet pet;
  @Transient
  private SalonSchedule sch;

}
