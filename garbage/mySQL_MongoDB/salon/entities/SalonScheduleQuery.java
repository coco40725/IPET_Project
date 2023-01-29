package temp.salon.entities;

import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.springframework.data.annotation.Id;


import java.io.Serial;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document("SALON_SCHEDULE")
public class SalonScheduleQuery extends Core {
  @Serial
  private static final long serialVersionUID = 4308779788527537373L;
  @Id
  @NonNull
  private Integer id;
  private Integer appointId;
  @NonNull
  private Date date;
  @NonNull
  private String period;
  @NonNull
  private String employeeNote;
  @NonNull
  private Document groomer;
  @NonNull
  private Document asst1;
  @NonNull
  private Document asst2;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    SalonScheduleQuery that = (SalonScheduleQuery) o;
    return id.equals(that.id) && Objects.equals(appointId, that.appointId) && date.equals(that.date) && period.equals(that.period) && employeeNote.equals(that.employeeNote) && groomer.equals(that.groomer) && asst1.equals(that.asst1) && asst2.equals(that.asst2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, appointId, date, period, employeeNote, groomer, asst1, asst2);
  }
}
