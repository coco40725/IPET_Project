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
@Table(name = "SALON_SERVICE_PET_TYPE")
public class SalonServicePetType extends Core {

  @Serial
  private static final long serialVersionUID = -7351901374176205160L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TYPE_ID")
  private Integer typeId;
  @Column(name = "TYPE_NAME")
  private String typeName;
  @Column(name = "PET_SIZE")
  private String petSize;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    SalonServicePetType that = (SalonServicePetType) o;
    return typeId != null && Objects.equals(typeId, that.typeId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
