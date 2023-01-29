package temp.staff.repositories;

import com.ipet.web.staff.entities.StaffRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:00
 */
@Repository
public interface StaffRoleRepository extends JpaRepository<StaffRole, StaffRole.PK> {
}
