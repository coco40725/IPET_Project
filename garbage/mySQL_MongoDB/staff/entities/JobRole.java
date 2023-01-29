package temp.staff.entities;

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
@Table(name = "JOB_ROLE")
public class JobRole extends Core {

  @Serial
  private static final long serialVersionUID = -1121758664132280017L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ROLE_ID")
  private Integer roleId;

  @Column(name = "ROLE_NAME")
  private String roleName;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    JobRole jobRole = (JobRole) o;
    return roleId != null && Objects.equals(roleId, jobRole.roleId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
