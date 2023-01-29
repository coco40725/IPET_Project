package temp.salon.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.springframework.data.annotation.Id;


import java.io.Serial;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document("SALON_APPOINTMENT")
public class SalonAppointmentQuery extends Core {
  @Serial
  private static final long serialVersionUID = 3267120031065105734L;
  @Id
  private Integer id;
  private String customerNote;
  @NonNull
  private Document mem;
  @NonNull
  private Document  pet;
  @NonNull
  private Document  sch;
  @NonNull
  private Integer totalPrice;
  @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
  private Date apmBuildTime;
  private Integer apmStatus;
  private List<Document> appointDetail;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SalonAppointmentQuery that = (SalonAppointmentQuery) o;
    return Objects.equals(id, that.id) && Objects.equals(customerNote, that.customerNote) && Objects.equals(mem, that.mem) && Objects.equals(pet, that.pet) && Objects.equals(sch, that.sch) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(apmBuildTime, that.apmBuildTime) && Objects.equals(apmStatus, that.apmStatus) && Objects.equals(appointDetail, that.appointDetail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerNote, mem, pet, sch, totalPrice, apmBuildTime, apmStatus, appointDetail);
  }
}
