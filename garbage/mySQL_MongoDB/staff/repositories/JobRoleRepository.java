package temp.staff.repositories;

import com.ipet.web.staff.entities.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 12:58
 */
@Repository
public interface JobRoleRepository extends JpaRepository<JobRole, Integer> {
}
