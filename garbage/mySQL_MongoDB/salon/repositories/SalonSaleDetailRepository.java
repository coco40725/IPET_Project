package temp.salon.repositories;

import com.ipet.web.salon.entities.SalonSaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:03
 */
@Repository
public interface SalonSaleDetailRepository extends JpaRepository<SalonSaleDetail, SalonSaleDetail.PK> {
    SalonSaleDetail findBySaleIdAndAndSvcId(Integer saleId, Integer svcId);
}
