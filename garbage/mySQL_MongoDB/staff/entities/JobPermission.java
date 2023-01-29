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
@Table(name = "JOB_PERMISSION")
public class JobPermission extends Core {

  @Serial
  private static final long serialVersionUID = 8109961990616643934L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERMISSION_ID")
  private Integer permissionId;

  @Column(name = "PERMISSION_NAME")
  private String permissionName;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    JobPermission that = (JobPermission) o;
    return permissionId != null && Objects.equals(permissionId, that.permissionId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
