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
@Table(name = "MEMBER")
public class Member extends Core {
  @Serial
  private static final long serialVersionUID = -7033276405122874821L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEM_ID")
  private Integer memId;
  @Column(name = "MEM_NAME")
  private String memName;
  @Column(name = "MEM_UID")
  private String memUid;
  @Column(name = "MEM_BIRTH")
  private Date memBirth;
  @Column(name = "MEM_SEX")
  private String memSex;
  @Column(name = "MEM_EMAIL")
  private String memEmail;
  @Column(name = "MEM_PHONE")
  private String memPhone;
  @Column(name = "MEM_TEL")
  private String memTel;
  @Column(name = "MEM_ADD")
  private String memAdd;
  @Column(name = "MEM_AC")
  private String memAc;
  @Column(name = "MEM_PW")
  private String memPw;
  @Column(name = "MEM_STATUS")
  private long memStatus;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Member member = (Member) o;
    return memId != null && Objects.equals(memId, member.memId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
