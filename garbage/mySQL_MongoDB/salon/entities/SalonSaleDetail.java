package temp.salon.entities;

import com.ipet.core.entities.Core;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SALON_SALE_DETAIL")
@IdClass(SalonSaleDetail.PK.class)
public class SalonSaleDetail extends Core {
  @Serial
  private static final long serialVersionUID = -5704002546354729863L;

  public static class PK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1981914101245882691L;
    @Id
    @Column(name = "SALE_ID")
    private Integer saleId;
    @Id
    @Column(name = "SVC_ID")
    private Integer svcId;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      PK pk = (PK) o;
      return Objects.equals(saleId, pk.saleId) && Objects.equals(svcId, pk.svcId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(saleId, svcId);
    }
  }

  @Id
  @Column(name = "SALE_ID")
  private Integer saleId;
  @Id
  @Column(name = "SVC_ID")
  private Integer svcId;
  @Column(name = "SALE_PRICE")
  private Integer salePrice;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    SalonSaleDetail that = (SalonSaleDetail) o;
    return saleId != null && Objects.equals(saleId, that.saleId)
            && svcId != null && Objects.equals(svcId, that.svcId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(saleId, svcId);
  }
}
