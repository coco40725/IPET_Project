package temp.news.entities;

import com.ipet.core.entities.Core;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NEWS")
public class News extends Core {

  @Serial
  private static final long serialVersionUID = 3912285891480787602L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "NEWS_ID")
  private Integer newsId;
  @Column(name = "NEWS_TITLE")
  private String newsTitle;
  @Column(name = "NEWS_TEXT")
  private String newsText;
  @Column(name = "NEWS_TIME")
  private java.sql.Timestamp newsTime;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    News news = (News) o;
    return newsId != null && Objects.equals(newsId, news.newsId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
