package temp.member.entities;

import com.ipet.core.entities.Core;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PET")
public class Pet extends Core {
  @Serial
  private static final long serialVersionUID = -8387489697658643051L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PET_ID")
  private Integer petId;
  @Column(name = "MEM_ID")
  private Integer memId;
  @Column(name = "PET_NAME")
  private String petName;

  @Transient
  private String memName;
  @Column(name = "PET_VAR_ID")
  private String petVarId;
  @Column(name = "PET_SIZE")
  private String petSize;
  @Column(name = "PET_GEN")
  private String petGen;
  @Column(name = "PET_BIRTH")
  private Date petBirth;
  @Column(name = "PET_STATUS")
  private String petStatus;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Pet pet = (Pet) o;
    return petId != null && Objects.equals(petId, pet.petId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
