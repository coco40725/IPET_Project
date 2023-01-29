package temp.salon.repositories;

import com.ipet.web.salon.entities.SalonScheduleQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:07
 */
@Repository
public interface SalonScheduleQueryRepository extends MongoRepository<SalonScheduleQuery, Integer> {
}
