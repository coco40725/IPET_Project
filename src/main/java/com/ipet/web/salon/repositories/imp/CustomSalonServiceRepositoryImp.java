package com.ipet.web.salon.repositories.imp;

import com.ipet.web.salon.entities.SalonService;
import com.ipet.web.salon.entities.unwinded.UnwindedSalonServices;
import com.ipet.web.salon.repositories.CustomSalonServiceRepository;
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
 * @create 2023-01-30-下午 03:18
 */
@Repository
public class CustomSalonServiceRepositoryImp implements CustomSalonServiceRepository {
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<UnwindedSalonServices> findAll() {
        TypedAggregation<SalonService> aggregation = Aggregation.newAggregation(
                SalonService.class,
                Aggregation.lookup("SALON_SERVICE_CATEGORY", "SVC_CATEGORY_ID", "_id", "SERVICE_CATEGORY"),
                Aggregation.lookup("SALON_SERVICE_PET_TYPE", "SALON_SERVICE_PET_TYPE_ID", "_id", "SERVICE_PET_TYPE")
        );
        AggregationResults<UnwindedSalonServices> aggregate = mongoTemplate.aggregate(aggregation, UnwindedSalonServices.class);
        return aggregate.getMappedResults();
    }

    @Override
    public UnwindedSalonServices findById(String id) {
        TypedAggregation<SalonService> aggregation = Aggregation.newAggregation(
                SalonService.class,
                Aggregation.match(Criteria.where("_id").is(id)),
                Aggregation.lookup("SALON_SERVICE_CATEGORY", "SVC_CATEGORY_ID", "_id", "SERVICE_CATEGORY"),
                Aggregation.lookup("SALON_SERVICE_PET_TYPE", "SALON_SERVICE_PET_TYPE_ID", "_id", "SERVICE_PET_TYPE")
        );
        AggregationResults<UnwindedSalonServices> aggregate = mongoTemplate.aggregate(aggregation, UnwindedSalonServices.class);
        return aggregate.getUniqueMappedResult();
    }

    @Override
    public void partialUpdate(SalonService salonService) {
        Document document = new Document();
        Update update = new Update();
        mongoTemplate.getConverter().write(salonService, document);
        document.forEach(update::set);
        SalonService updateService = mongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(salonService.getId())), update, new FindAndModifyOptions().returnNew(true), SalonService.class);

        if (updateService != null){
            mongoTemplate.save(updateService);
        }
    }
}
