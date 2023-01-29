package com.ipet.web.salon.entities;


import com.ipet.core.entities.Core;
import com.ipet.web.member.entities.Member;
import com.ipet.web.member.entities.Pet;
import com.ipet.web.staff.entities.Staff;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SALON_APPOINTMENT")
public class SalonAppointment extends Core {
  @Serial
  private static final long serialVersionUID = 3851236588701641094L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "APM_ID")
  private Integer apmId;
  @Column(name = "APM_STATUS")
  private Integer apmStatus;
  @Column(name = "MEM_ID")
  private Integer memId;
  @Column(name = "PET_ID")
  private Integer petId;
  @Column(name = "SCH_ID")
  private Integer schId;
  @Column(name = "APM_BUILD_TIME")
  private Timestamp apmBuildTime;
  @Column(name = "CUSTOMER_NOTE")
  private String customerNote;
  @Column(name = "TOTAL_PRICE")
  private Integer totalPrice;

  @Transient
  private Member mem;
  @Transient
  private Pet pet;
  @Transient
  private SalonSchedule sch;
  @Transient
  private List<SalonAppointmentDetail> salonAppointmentDetails;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    SalonAppointment that = (SalonAppointment) o;
    return apmId != null && Objects.equals(apmId, that.apmId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
