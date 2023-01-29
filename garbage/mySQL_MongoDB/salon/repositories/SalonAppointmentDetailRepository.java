package temp.salon.repositories;

import com.ipet.web.salon.entities.SalonAppointmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:02
 */
@Repository
public interface SalonAppointmentDetailRepository extends JpaRepository<SalonAppointmentDetail, SalonAppointmentDetail.PK> {
     List<SalonAppointmentDetail> findAllByApmId(Integer id);
}
