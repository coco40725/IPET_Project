package com.ipet.web.staff.entities;

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
@Table(name = "STAFF_ROLE")
@IdClass(StaffRole.PK.class)
public class StaffRole extends Core {

  @Serial
  private static final long serialVersionUID = 8180260844461843624L;

  public static class PK implements Serializable {
    @Serial
    private static final long serialVersionUID = -308792372503092012L;
    @Id
    @Column(name = "STAFF_ID")
    private Integer staffId;

    @Id
    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
      PK pk = (PK) o;
      return staffId != null && Objects.equals(staffId, pk.staffId)
              && roleId != null && Objects.equals(roleId, pk.roleId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(staffId, roleId);
    }
  }

  @Id
  @Column(name = "STAFF_ID")
  private Integer staffId;

  @Id
  @Column(name = "ROLE_ID")
  private Integer roleId;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    StaffRole staffRole = (StaffRole) o;
    return staffId != null && Objects.equals(staffId, staffRole.staffId)
            && roleId != null && Objects.equals(roleId, staffRole.roleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(staffId, roleId);
  }
}
