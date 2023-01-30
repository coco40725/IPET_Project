package com.ipet.web.member.repositories.imp;

import com.ipet.web.member.entities.Member;
import com.ipet.web.member.entities.unwinded.UnwindedMember;
import com.ipet.web.member.repositories.CustomMemberRepository;
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
 * @create 2023-01-29-下午 12:07
 */
@Repository
public class CustomMemberRepositoryImp implements CustomMemberRepository {
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    /*
    * Aggregation.lookup(外表在 Mongodb 的名稱, "mongodb 語法: 本表的fk" , "外表在 Mongodb 的pk名稱", "欲使用哪種名稱存 petDetail")
    * */

    @Override
    public List<UnwindedMember> findAll() {
        TypedAggregation<Member> aggregation = Aggregation.newAggregation(
                Member.class,
                Aggregation.lookup("PET", "PET.REF_ID", "_id", "PET_DETAIL") // 這裡以 MongoDB 為主的名稱與語法
        );
        AggregationResults<UnwindedMember> results =  mongoTemplate.aggregate(aggregation, UnwindedMember.class);
        return results.getMappedResults();
    }

    @Override
    public UnwindedMember findById(String id) {
        TypedAggregation<Member> aggregation = Aggregation.newAggregation(
                Member.class,
                Aggregation.match(Criteria.where("_id").is(id)),
                Aggregation.lookup("PET", "PET.REF_ID","_id","PET_DETAIL")
        );
        AggregationResults<UnwindedMember> results = mongoTemplate.aggregate(aggregation, UnwindedMember.class);
        return results.getUniqueMappedResult();
    }
}
