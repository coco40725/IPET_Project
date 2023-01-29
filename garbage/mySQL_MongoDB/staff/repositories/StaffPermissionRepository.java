package temp.staff.repositories;

import com.ipet.web.staff.entities.StaffPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 12:59
 */
@Repository
public interface StaffPermissionRepository extends JpaRepository<StaffPermission, StaffPermission.PK> {
}
