package com.ipet.web.salon.repositories.imp;

import com.ipet.web.member.entities.Member;
import com.ipet.web.salon.entities.SalonAppointment;
import com.ipet.web.salon.entities.unwinded.UnwindedSalonAppointment;
import com.ipet.web.salon.repositories.CustomAppointmentRepository;
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
 * @create 2023-01-29-下午 03:44
 */
@Repository
public class CustomAppointmentRepositoryImp implements CustomAppointmentRepository {
    private MongoTemplate mongoTemplate;
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<UnwindedSalonAppointment> findAll() {
        TypedAggregation<SalonAppointment> aggregation = Aggregation.newAggregation(
                SalonAppointment.class,
                Aggregation.lookup("MEMBER", "MEM_ID", "_id", "MEM"),
                Aggregation.lookup("PET","PET_ID", "_id", "PET"),
                Aggregation.lookup("SALON_SCHEDULE","SCH_ID", "_id", "SCH")
        );
        AggregationResults<UnwindedSalonAppointment> aggregate = mongoTemplate.aggregate(aggregation, UnwindedSalonAppointment.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<UnwindedSalonAppointment> findAllByApmStatus(Integer apmStatus) {
        TypedAggregation<SalonAppointment> aggregation = Aggregation.newAggregation(
                SalonAppointment.class,
                Aggregation.match(Criteria.where("APM_STATUS").is(apmStatus)),
                Aggregation.lookup("MEMBER", "MEM_ID", "_id", "MEM"),
                Aggregation.lookup("PET","PET_ID", "_id", "PET"),
                Aggregation.lookup("SALON_SCHEDULE","SCH_ID", "_id", "SCH")
        );
        AggregationResults<UnwindedSalonAppointment> aggregate = mongoTemplate.aggregate(aggregation, UnwindedSalonAppointment.class);
        return aggregate.getMappedResults();
    }

    @Override
    public UnwindedSalonAppointment findById(String id) {
        TypedAggregation<SalonAppointment> aggregation = Aggregation.newAggregation(
                SalonAppointment.class,
                Aggregation.match(Criteria.where("id").is(id)),
                Aggregation.lookup("MEMBER", "MEM_ID", "_id", "MEM"),
                Aggregation.lookup("PET","PET_ID", "_id", "PET"),
                Aggregation.lookup("SALON_SCHEDULE","SCH_ID", "_id", "SCH")
        );
        AggregationResults<UnwindedSalonAppointment> aggregate = mongoTemplate.aggregate(aggregation, UnwindedSalonAppointment.class);
        return aggregate.getUniqueMappedResult();
    }

    @Override
    public void partialUpdate(SalonAppointment salonAppointment) {
        Document document = new Document();
        // 將 member 轉成 Document 格式 (如此 null 的field會直接消失) 並寫入 document
        mongoTemplate.getConverter().write(salonAppointment, document);
        Update update = new Update();
        // 重複執行 update.set("name","value")
        document.forEach(update::set);
        SalonAppointment updateAppoint = mongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(salonAppointment.getId())),update, new FindAndModifyOptions().returnNew(true), SalonAppointment.class);

        if (updateAppoint != null) {
            mongoTemplate.save(updateAppoint);
        }
    }
}
