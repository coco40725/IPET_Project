package com.ipet.web.salon.entities;

import com.ipet.core.entities.Core;
import com.ipet.web.staff.entities.Staff;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Document("SALON_SCHEDULE")
@AllArgsConstructor
@NoArgsConstructor
public class SalonSchedule extends Core {

  @Serial
  private static final long serialVersionUID = -5268348271833663921L;

  @Id
  @NonNull
  private String id;
  @Field("ASST1")
  @NonNull
  @DBRef
  private Staff asst1;
  @Field("ASST2")
  @NonNull
  @DBRef
  private Staff asst2;
  @Field("EMPLOYEE_NOTE")
  private String employeeNote;
  @Field("GROOMER")
  @NonNull
  @DBRef
  private Staff groomer;
  @Field("SCH_DATE")
  @NonNull
  private Date schDate;
  @Field("SCH_PERIOD")
  @NonNull
  private String schPeriod;

  @Transient
  private SalonAppointment salonAppointment;

}
