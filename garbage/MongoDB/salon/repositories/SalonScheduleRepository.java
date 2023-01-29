package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-27-下午 05:08
 */
@Repository
public interface SalonScheduleRepository extends MongoRepository<SalonSchedule, String> {
    List<SalonSchedule> findAllBySchDateAndSchPeriodAndIdNot(Date schDate, String schPeriod, String id);
    List<SalonSchedule> findAllBySchDateAndSchPeriod(Date schDate, String schPeriod);
    List<SalonSchedule> findAllBySchDateAfterAndSchPeriod(Date date, String schPeriod);
}
