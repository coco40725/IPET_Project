package temp.staff.entities;


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
@Table(name = "STAFF")
public class Staff extends Core {

  @Serial
  private static final long serialVersionUID = 2243711124926185486L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "STAFF_ID")
  private Integer staffId;
  @Column(name = "STAFF_NAME")
  private String staffName;
  @Column(name = "STAFF_UID")
  private String staffUid;
  @Column(name = "STAFF_BIRTH")
  private Date staffBirth;
  @Column(name = "STAFF_SEX")
  private String staffSex;
  @Column(name = "STAFF_EMAIL")
  private String staffEmail;
  @Column(name = "STAFF_PHONE")
  private String staffPhone;
  @Column(name = "STAFF_TEL")
  private String staffTel;
  @Column(name = "STAFF_ADD")
  private String staffAdd;
  @Column(name = "STAFF_AC")
  private String staffAc;
  @Column(name = "STAFF_PW")
  private String staffPw;
  @Column(name = "STAFF_STATUS")
  private Integer staffStatus;
  @Column(name = "STAFF_POSI")
  private String staffPosi;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Staff staff = (Staff) o;
    return staffId != null && Objects.equals(staffId, staff.staffId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
