package temp.salon.entities;

import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.springframework.data.annotation.Id;


import java.io.Serial;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document("SALON_SERVICE")
public class SalonServiceQuery extends Core {

  @Serial
  private static final long serialVersionUID = -5636554526515141798L;
  @Id
  @NonNull
  private Integer id;
  @NonNull
  private Document pet;
  private Document sale;
  private String svcContent;
  @NonNull
  private String svcName;
  @NonNull
  private Integer svcPrice;
  @NonNull
  private Integer svcStatus;

  @NonNull
  private Document svcCategory;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    SalonServiceQuery that = (SalonServiceQuery) o;
    return id.equals(that.id) && pet.equals(that.pet) && Objects.equals(sale, that.sale) && Objects.equals(svcContent, that.svcContent) && svcName.equals(that.svcName) && svcPrice.equals(that.svcPrice) && svcStatus.equals(that.svcStatus) && svcCategory.equals(that.svcCategory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, pet, sale, svcContent, svcName, svcPrice, svcStatus, svcCategory);
  }
}
