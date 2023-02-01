package com.ipet.web.staff.repositories.imp;

import com.ipet.web.staff.entities.JobRole;
import com.ipet.web.staff.entities.unwinded.UnwindedJobRole;
import com.ipet.web.staff.repositories.CustomJobRoleRepository;
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
 * @create 2023-02-01-下午 08:02
 */
@Repository
public class CustomJobRoleRepositoryImp implements CustomJobRoleRepository {
    private MongoTemplate mongoTemplate;
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public List<UnwindedJobRole> findAll() {
        TypedAggregation<JobRole> aggregation = Aggregation.newAggregation(
                JobRole.class,
                Aggregation.lookup("JOB_PERMISSION", "STAFF_PERMISSION_IDS", "_id","JOB_PERMISSION")
        );
        AggregationResults<UnwindedJobRole> aggregate = mongoTemplate.aggregate(aggregation, UnwindedJobRole.class);
        return aggregate.getMappedResults();
    }

    @Override
    public UnwindedJobRole findById(String id) {
        TypedAggregation<JobRole> aggregation = Aggregation.newAggregation(
                JobRole.class,
                Aggregation.match(Criteria.where("_id").is(id)),
                Aggregation.lookup("JOB_PERMISSION", "STAFF_PERMISSION_IDS", "_id","JOB_PERMISSION")
        );
        AggregationResults<UnwindedJobRole> aggregate = mongoTemplate.aggregate(aggregation, UnwindedJobRole.class);
        return aggregate.getUniqueMappedResult();
    }
}
