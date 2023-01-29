package com.ipet.web.salon.repositories.imp;

import com.ipet.web.salon.entities.SalonAppointment;
import com.ipet.web.salon.entities.result.ResultSalonAppointment;
import com.ipet.web.salon.repositories.CustomAppointmentRepository;
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
 * @create 2023-01-29-下午 03:44
 */
@Repository
public class CustomAppointmentRepositpryImp implements CustomAppointmentRepository {
    private MongoTemplate mongoTemplate;
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ResultSalonAppointment> findAll() {
        TypedAggregation<SalonAppointment> aggregation = Aggregation.newAggregation(
                SalonAppointment.class,
                Aggregation.lookup("MEMBER", "MEM_ID", "_id", "MEM"),
                Aggregation.lookup("PET","PET_ID", "_id", "PET"),
                Aggregation.lookup("SALON_SCHEDULE","SCH_ID", "_id", "SCH")
        );
        AggregationResults<ResultSalonAppointment> aggregate = mongoTemplate.aggregate(aggregation, ResultSalonAppointment.class);
        System.out.println(aggregate.getMappedResults());
        return aggregate.getMappedResults();
    }

    @Override
    public List<ResultSalonAppointment> findAllByApmStatus(Integer apmStatus) {
        TypedAggregation<SalonAppointment> aggregation = Aggregation.newAggregation(
                SalonAppointment.class,
                Aggregation.match(Criteria.where("APM_STATUS").is(apmStatus)),
                Aggregation.lookup("MEMBER", "MEM_ID", "_id", "MEM"),
                Aggregation.lookup("PET","PET_ID", "_id", "PET"),
                Aggregation.lookup("SALON_SCHEDULE","SCH_ID", "_id", "SCH"),
                Aggregation.lookup("SALON_SERVICE","SVC_ID", "_id", "SVC"),
                Aggregation.lookup("SALON_SALE","SALE_ID", "_id", "SALE")
        );
        AggregationResults<ResultSalonAppointment> aggregate = mongoTemplate.aggregate(aggregation, ResultSalonAppointment.class);
        System.out.println(aggregate);
        return aggregate.getMappedResults();
    }

    @Override
    public ResultSalonAppointment findById(String id) {
        return null;
    }
}
