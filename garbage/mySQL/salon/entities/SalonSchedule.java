package com.ipet.web.salon.entities;


import com.ipet.core.entities.Core;
import com.ipet.web.staff.entities.Staff;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SALON_SCHEDULE")
public class SalonSchedule extends Core {
  @Serial
  private static final long serialVersionUID = -7212339918339341398L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SCH_ID")
  private Integer schId;
  @Column(name = "SCH_DATE")
  private Date schDate;
  @Column(name = "SCH_PERIOD")
  private String schPeriod;
  @Column(name = "GROOMER_ID")
  private Integer groomerId;
  @Column(name = "ASST_ID_1")
  private Integer asstId1;
  @Column(name = "ASST_ID_2")
  private Integer asstId2;
  @Column(name = "EMPLOYEE_NOTE")
  private String employeeNote;

  @Transient
  private SalonAppointment salonAppointment;
  @Transient
  private Staff groomer;
  @Transient
  private Staff asst1;
  @Transient
  private Staff asst2;
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    SalonSchedule that = (SalonSchedule) o;
    return schId != null && Objects.equals(schId, that.schId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
