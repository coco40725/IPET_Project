package temp.salon.entities;


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
@Table(name = "SALON_SERVICE")
public class SalonService extends Core {

  @Serial
  private static final long serialVersionUID = -3156115728721422665L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SVC_ID")
  private Integer svcId;
  @Column(name = "SVC_NAME")
  private String svcName;
  @Column(name = "SVC_CONTENT")
  private String svcContent;
  @Column(name = "SVC_CATEGORY_ID")
  private Integer svcCategoryId;
  @Column(name = "TYPE_ID")
  private Integer typeId;
  @Column(name = "SVC_PRICE")
  private Integer svcPrice;
  @Column(name = "SVC_STATUS")
  private Integer svcStatus;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    SalonService that = (SalonService) o;
    return svcId != null && Objects.equals(svcId, that.svcId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
