package com.ipet.web.salon.entities;


import com.ipet.core.entities.Core;
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
@Table(name = "SALON_SALE")
public class SalonSale extends Core {
  @Serial
  private static final long serialVersionUID = -1966515264717428675L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SALE_ID")
  private Integer saleId;
  @Column(name = "SALE_NAME")
  private String saleName;
  @Column(name = "SALE_CONTENT")
  private String saleContent;
  @Column(name = "START_TIME")
  private Timestamp startTime;
  @Column(name = "END_TIME")
  private Timestamp endTime;
  @Transient
  private List<SalonService> salonServices;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    SalonSale salonSale = (SalonSale) o;
    return saleId != null && Objects.equals(saleId, salonSale.saleId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
