package temp.staff.repositories;

import com.ipet.web.staff.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 12:59
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findAllByStaffPosi(String posi);
}
