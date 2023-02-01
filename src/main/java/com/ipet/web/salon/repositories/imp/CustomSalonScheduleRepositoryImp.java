package com.ipet.web.salon.repositories.imp;

import com.ipet.web.salon.entities.SalonAppointment;
import com.ipet.web.salon.entities.SalonSchedule;
import com.ipet.web.salon.entities.unwinded.UnwindedSalonSchedule;
import com.ipet.web.salon.repositories.CustomSalonScheduleRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 10:33
 */
@Repository
public class CustomSalonScheduleRepositoryImp implements CustomSalonScheduleRepository {
    private MongoTemplate mongoTemplate;
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public List<UnwindedSalonSchedule> findAll() {
        TypedAggregation<SalonSchedule> aggregation = Aggregation.newAggregation(
                SalonSchedule.class,
                Aggregation.lookup("STAFF", "GROOMER_ID", "_id", "GROOMER"),
                Aggregation.lookup("STAFF", "ASST1_ID", "_id", "ASST1"),
                Aggregation.lookup("STAFF", "ASST2_ID", "_id", "ASST2")
        );
        AggregationResults<UnwindedSalonSchedule> aggregate = mongoTemplate.aggregate(aggregation, UnwindedSalonSchedule.class);
        return aggregate.getMappedResults();
    }

    @Override
    public UnwindedSalonSchedule findById(String id) {
        TypedAggregation<SalonSchedule> aggregation = Aggregation.newAggregation(
                SalonSchedule.class,
                Aggregation.match(Criteria.where("_id").is(id)),
                Aggregation.lookup("STAFF", "GROOMER_ID", "_id", "GROOMER"),
                Aggregation.lookup("STAFF", "ASST1_ID", "_id", "ASST1"),
                Aggregation.lookup("STAFF", "ASST2_ID", "_id", "ASST2")
        );
        AggregationResults<UnwindedSalonSchedule> aggregate = mongoTemplate.aggregate(aggregation, UnwindedSalonSchedule.class);
        return aggregate.getUniqueMappedResult();
    }

    @Override
    public void partialUpdate(SalonSchedule schedule) {
        Document document = new Document();
        // 將 member 轉成 Document 格式 (如此 null 的field會直接消失) 並寫入 document
        mongoTemplate.getConverter().write(schedule, document);
        Update update = new Update();
        // 重複執行 update.set("name","value")
        document.forEach(update::set);
        SalonSchedule updateSchedule = mongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(schedule.getId())),update, new FindAndModifyOptions().returnNew(true), SalonSchedule.class);

        if (updateSchedule != null) {
            mongoTemplate.save(updateSchedule);
        }
    }
}
