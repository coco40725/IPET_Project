package temp.staff.repositories;

import com.ipet.web.staff.entities.Staff;
import com.ipet.web.staff.entities.StaffQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:09
 */
@Repository
public interface StaffQueryRepository extends MongoRepository<StaffQuery, Integer> {
}
