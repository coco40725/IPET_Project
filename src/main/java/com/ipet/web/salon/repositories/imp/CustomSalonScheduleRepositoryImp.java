package com.ipet.web.salon.repositories;

import com.ipet.web.salon.entities.SalonSchedule;
import com.ipet.web.salon.entities.result.ResultSalonAppointment;
import com.ipet.web.salon.entities.result.ResultSalonSchedule;
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
public class CustomSalonScheduleRepositoryImp implements CustomSalonScheduleRepository{
    private MongoTemplate mongoTemplate;
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public List<ResultSalonSchedule> findAll() {
        TypedAggregation<SalonSchedule> aggregation = Aggregation.newAggregation(
                SalonSchedule.class,
                Aggregation.lookup("STAFF", "GROOMER_ID", "_id", "GROOMER"),
                Aggregation.lookup("STAFF", "ASST1_ID", "_id", "ASST1"),
                Aggregation.lookup("STAFF", "ASST2_ID", "_id", "ASST2")
        );
        AggregationResults<ResultSalonSchedule> aggregate = mongoTemplate.aggregate(aggregation, ResultSalonSchedule.class);
        return aggregate.getMappedResults();
    }

    @Override
    public ResultSalonSchedule findById(String id) {
        TypedAggregation<SalonSchedule> aggregation = Aggregation.newAggregation(
                SalonSchedule.class,
                Aggregation.match(Criteria.where("_id").is(id)),
                Aggregation.lookup("STAFF", "GROOMER_ID", "_id", "GROOMER"),
                Aggregation.lookup("STAFF", "ASST1_ID", "_id", "ASST1"),
                Aggregation.lookup("STAFF", "ASST2_ID", "_id", "ASST2")
        );
        AggregationResults<ResultSalonSchedule> aggregate = mongoTemplate.aggregate(aggregation, ResultSalonSchedule.class);
        return aggregate.getUniqueMappedResult();
    }
}
