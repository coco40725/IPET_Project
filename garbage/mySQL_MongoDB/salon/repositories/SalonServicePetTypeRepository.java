package temp.salon.repositories;

import com.ipet.web.salon.entities.SalonServicePetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:05
 */
@Repository
public interface SalonServicePetTypeRepository extends JpaRepository<SalonServicePetType, Integer> {
}
