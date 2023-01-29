package com.ipet.web.member.repositories;

import com.ipet.web.member.entities.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 10:02
 */
@Repository
public class CustomPetRepositoryImp implements CustomPetRepository{
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Pet findById(String id) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("pet"),
                Aggregation.lookup("Pet", "pet", "id", "petDetail")
        );
        return null;
    }

    @Override
    public List<Pet> findAll() {
        return null;
    }
}
