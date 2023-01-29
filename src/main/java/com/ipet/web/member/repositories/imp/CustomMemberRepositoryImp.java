package com.ipet.web.member.repositories;

import com.ipet.web.member.entities.Member;
import com.ipet.web.member.entities.result.ResultMember;
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
public class CustomMemberRepositoryImp implements CustomMemberRepository{
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    /*
    * Aggregation.lookup(外表在 Mongodb 的名稱, "mongodb 語法: 本表的fk" , "外表在 Mongodb 的pk名稱", "欲使用哪種名稱存 petDetail")
    * */

    @Override
    public List<ResultMember> findAll() {
        TypedAggregation<Member> aggregation = Aggregation.newAggregation(
                Member.class,
                Aggregation.lookup("PET", "PET.REF_ID", "_id", "PET_DETAIL") // 這裡以 MongoDB 為主的名稱與語法
        );
        AggregationResults<ResultMember> results =  mongoTemplate.aggregate(aggregation, ResultMember.class);
        return results.getMappedResults();
    }

    @Override
    public ResultMember findById(String id) {
        TypedAggregation<Member> aggregation = Aggregation.newAggregation(
                Member.class,
                Aggregation.match(Criteria.where("_id").is(id)),
                Aggregation.lookup("PET", "PET.REF_ID","_id","PET_DETAIL")
        );
        AggregationResults<ResultMember> results = mongoTemplate.aggregate(aggregation, ResultMember.class);
        return results.getUniqueMappedResult();
    }
}
