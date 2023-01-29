package com.ipet.web.salon.entities;

import com.ipet.core.entities.Core;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SALON_APPOINTMENT_DETAIL")
@IdClass(SalonAppointmentDetail.PK.class)
public class SalonAppointmentDetail extends Core {

  @Serial
  private static final long serialVersionUID = -7653495209734421433L;

  public static class PK implements Serializable {
    @Serial
    private static final long serialVersionUID = -5640493609101753851L;
    @Id
    @Column(name = "APM_ID")
    private Integer apmId;
    @Id
    @Column(name = "SVC_ID")
    private Integer svcId;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      PK pk = (PK) o;
      return Objects.equals(apmId, pk.apmId) && Objects.equals(svcId, pk.svcId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(apmId, svcId);
    }
  }

  @Id
  @Column(name = "APM_ID")
  private Integer apmId;
  @Id
  @Column(name = "SVC_ID")
  private Integer svcId;
  @Column(name = "SALE_ID")
  private Integer saleId;
  @Column(name = "SVC_PRICE")
  private Integer svcPrice;
  @Column(name = "SALE_PRICE")
  private Integer salePrice;
  @Transient
  private SalonService salonService;
  @Transient
  private SalonSale salonSale;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    SalonAppointmentDetail that = (SalonAppointmentDetail) o;
    return apmId != null && Objects.equals(apmId, that.apmId)
            && svcId != null && Objects.equals(svcId, that.svcId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apmId, svcId);
  }
}
