package com.ipet.web.salon.entities;

import com.ipet.core.entities.Core;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SALON_SERVICE_CATEGORY")
public class SalonServiceCategory extends Core {

  @Serial
  private static final long serialVersionUID = 8437657160867805967L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SVC_CATEGORY_ID")
  private Integer svcCategoryId;
  @Column(name = "SVC_CATEGORY_NAME")
  private String svcCategoryName;
  @Column(name = "SVC_CATEGORY_IMG")
  private Byte[] svcCategoryImg;
  @Column(name = "SVC_CATEGORY_STATUS")
  private Integer svcCategoryStatus;
  @Transient
  private String svcCategoryImgBase64;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    SalonServiceCategory that = (SalonServiceCategory) o;
    return svcCategoryId != null && Objects.equals(svcCategoryId, that.svcCategoryId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
