package com.ipet.web.salon.repositories.imp;

import com.ipet.web.salon.entities.SalonSale;
import com.ipet.web.salon.repositories.CustomAppointmentRepository;
import com.ipet.web.salon.repositories.CustomSaleRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023/2/3 上午 09:46
 */
@Repository
public class CustomSaleRepositoryImp implements CustomSaleRepository {
    private MongoTemplate mongoTemplate;
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public void partialUpdate(SalonSale salonSale) {
        Document document = new Document();
        Update update = new Update();
        mongoTemplate.getConverter().write(salonSale, document);
        document.forEach(update::set);
        SalonSale updateSale = mongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(salonSale.getId())), update, new FindAndModifyOptions().returnNew(true), SalonSale.class);
        if (updateSale != null){
            mongoTemplate.save(updateSale);
        }
    }
}
