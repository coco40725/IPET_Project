package temp.salon.repositories;

import com.ipet.web.salon.entities.SalonServiceQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:08
 */
@Repository
public interface SalonServiceQueryRepository extends MongoRepository<SalonServiceQuery, Integer> {
}
