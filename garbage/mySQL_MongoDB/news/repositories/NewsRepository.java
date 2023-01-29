package temp.news.repositories;

import com.ipet.web.news.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 12:54
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
}
