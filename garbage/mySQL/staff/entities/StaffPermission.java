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
@Table(name = "STAFF_PERMISSION")
@IdClass(StaffPermission.PK.class)
public class StaffPermission extends Core {
  @Serial
  private static final long serialVersionUID = 6740293527076019515L;

  public static class PK implements Serializable {
    @Serial
    private static final long serialVersionUID = -6472559913102308457L;
    @Id
    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Id
    @Column(name = "PERMISSION_ID")
    private Integer permissionId;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      PK pk = (PK) o;
      return Objects.equals(roleId, pk.roleId) && Objects.equals(permissionId, pk.permissionId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(roleId, permissionId);
    }
  }

  @Id
  @Column(name = "ROLE_ID")
  private Integer roleId;
  @Id
  @Column(name = "PERMISSION_ID")
  private Integer permissionId;



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    StaffPermission that = (StaffPermission) o;
    return roleId != null && Objects.equals(roleId, that.roleId)
            && permissionId != null && Objects.equals(permissionId, that.permissionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roleId, permissionId);
  }
}
