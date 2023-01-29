package temp.staff.entities;

import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serial;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("STAFF")
public class StaffQuery extends Core {
  @Serial
  private static final long serialVersionUID = -5260822826126488072L;
  @Id
  @NonNull
  private Integer id;
  @NonNull
  private String staffName;
  @NonNull
  private Document staffRight;
  @NonNull
  private String staffAdd;
  @NonNull
  private Date staffBirth;
  @NonNull
  private String staffEmail;
  @NonNull
  private String staffPhone;
  @NonNull
  private String staffPosi;
  @NonNull
  private String staffAc;
  @NonNull
  private String staffPw;
  @NonNull
  private String staffSex;
  @NonNull
  private Integer staffStatus;
  private String staffTel;
  @NonNull
  private String staffUid;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    StaffQuery that = (StaffQuery) o;
    return id.equals(that.id) && staffName.equals(that.staffName) && staffRight.equals(that.staffRight) && staffAdd.equals(that.staffAdd) && staffBirth.equals(that.staffBirth) && staffEmail.equals(that.staffEmail) && staffPhone.equals(that.staffPhone) && staffPosi.equals(that.staffPosi) && staffAc.equals(that.staffAc) && staffPw.equals(that.staffPw) && staffSex.equals(that.staffSex) && staffStatus.equals(that.staffStatus) && Objects.equals(staffTel, that.staffTel) && staffUid.equals(that.staffUid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, staffName, staffRight, staffAdd, staffBirth, staffEmail, staffPhone, staffPosi, staffAc, staffPw, staffSex, staffStatus, staffTel, staffUid);
  }
}
