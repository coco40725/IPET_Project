package com.ipet.web.member.repositories.imp;

import com.ipet.web.member.entities.Member;
import com.ipet.web.member.entities.Pet;
import com.ipet.web.member.repositories.CustomPetRepository;
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
 * @create 2023-02-01-下午 04:54
 */
@Repository
public class CustomPetRepositoryImp implements CustomPetRepository {
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void partialUpdate(Pet pet) {
        Document document = new Document();
        // 將 member 轉成 Document 格式 (如此 null 的field會直接消失) 並寫入 document
        mongoTemplate.getConverter().write(pet, document);
        Update update = new Update();
        // 重複執行 update.set("name","value")
        document.forEach(update::set);
        Pet updatePet = mongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(pet.getId())),update, new FindAndModifyOptions().returnNew(true), Pet.class);
        if (updatePet != null) {
            mongoTemplate.save(updatePet);
        }
    }
}
