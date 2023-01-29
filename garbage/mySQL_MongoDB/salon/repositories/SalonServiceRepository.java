package temp.salon.repositories;

import com.ipet.web.salon.entities.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:04
 */
@Repository
public interface SalonServiceRepository extends JpaRepository<SalonService, Integer> {
}
