package com.ipet.web.salon.repositories.imp;

import com.ipet.web.salon.entities.SalonSchedule;
import com.ipet.web.salon.entities.unwinded.UnwindedSalonSchedule;
import com.ipet.web.salon.repositories.CustomSalonScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
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
}
