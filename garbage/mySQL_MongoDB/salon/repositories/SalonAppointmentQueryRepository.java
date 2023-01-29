package temp.salon.repositories;

import com.ipet.web.salon.entities.SalonAppointmentQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:07
 */
@Repository
public interface SalonAppointmentQueryRepository extends MongoRepository<SalonAppointmentQuery, Integer> {
     List<SalonAppointmentQuery> findByApmStatus(Integer apmStatus);
}
