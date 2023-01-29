package temp.salon.repositories;

import com.ipet.web.salon.entities.SalonSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:03
 */
@Repository
public interface SalonScheduleRepository extends JpaRepository<SalonSchedule, Integer> {
    // findIllegalDatesToAddJobs
    // 選定 美容師, 助理1, 助理2 與時段後
    // 回傳以今天後的3個月，哪幾個日期
    // 1. 班表數量 = 2
    // 2. 或 有重複的員工
     String GET_ILLEGAL_DATES = "SELECT SCH_DATE\n" +
            "FROM SALON_SCHEDULE\n" +
            "WHERE SCH_DATE >= :date AND SCH_PERIOD = :period AND \n" +
            "      (GROOMER_ID = :groomerID OR ASST_ID_1 = :asstID1 OR ASST_ID_2 = :asstID2 OR ASST_ID_1 = :asstID2 OR ASST_ID_2 = :asstID1)\n" +
            "UNION\n" +
            "SELECT SCH_DATE\n" +
            "FROM SALON_SCHEDULE\n" +
            "WHERE SCH_DATE >= :date AND SCH_PERIOD = :period \n" +
            "GROUP BY SCH_DATE\n" +
            "HAVING count(1) = 2";
    @Query(value = GET_ILLEGAL_DATES, nativeQuery = true)

    Set<Object> findIllegalDatesToAddJobs(@Param("date") Date date, @Param("period") String period,
                                          @Param("groomerID") Integer groomerID, @Param("asstID1") Integer asstID1, @Param("asstID2") Integer asstID2);

    List<SalonSchedule> findAllBySchDateAndSchPeriodAndSchIdNot(Date date, String period, Integer id);
    List<SalonSchedule> findAllBySchDateAndSchPeriod(Date date, String period);

}
