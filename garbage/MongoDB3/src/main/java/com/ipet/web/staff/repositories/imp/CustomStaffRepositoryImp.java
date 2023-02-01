package com.ipet.web.staff.repositories.imp;

import com.ipet.web.staff.entities.Staff;
import com.ipet.web.staff.entities.unwinded.UnwindedStaff;
import com.ipet.web.staff.repositories.CustomStaffRepository;
import org.bson.Document;
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
 * @create 2023-01-31-上午 10:58
 */
@Repository
public class CustomStaffRepositoryImp implements CustomStaffRepository {
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<UnwindedStaff> findAll() {
        TypedAggregation<Staff> aggregation = Aggregation.newAggregation(
                Staff.class,
                Aggregation.lookup("JOB_ROLE", "STAFF_ROLE_IDS.REF_ID", "_id", "JOB_ROLES"),
                Aggregation.unwind("JOB_ROLES"), // 不能進行 nested look up ，必須先拆分
                Aggregation.lookup("JOB_PERMISSION", "JOB_ROLES.STAFF_PERMISSION_IDS.REF_ID","_id","JOB_ROLES.JOB_PERMISSION"),
                Aggregation.group("_id")
                        .first("STAFF_NAME").as("STAFF_NAME")
                        .first("STAFF_UID").as("STAFF_UID")
                        .first("STAFF_SEX").as("STAFF_SEX")
                        .first("STAFF_EMAIL").as("STAFF_EMAIL")
                        .first("STAFF_PHONE").as("STAFF_PHONE")
                        .first("STAFF_TEL").as("STAFF_TEL")
                        .first("STAFF_ADD").as("STAFF_ADD")
                        .first("STAFF_AC").as("STAFF_AC")
                        .first("STAFF_PW").as("STAFF_PW")
                        .first("STAFF_STATUS").as("STAFF_STATUS")
                        .first("STAFF_POSI").as("STAFF_POSI")
                        .first("STAFF_BIRTH").as("STAFF_BIRTH")
                        .push("JOB_ROLES").as("JOB_ROLES")
                );
        AggregationResults<UnwindedStaff> aggregate = mongoTemplate.aggregate(aggregation, UnwindedStaff.class);
        return aggregate.getMappedResults();
    }

    @Override
    public UnwindedStaff findById(String id) {
        TypedAggregation<Staff> aggregation = Aggregation.newAggregation(
                Staff.class,
                Aggregation.match(Criteria.where("_id").is(id)),
                Aggregation.lookup("JOB_ROLE", "STAFF_ROLE_IDS.REF_ID", "_id", "JOB_ROLES"),
                Aggregation.unwind("JOB_ROLES"), // 不能進行 nested look up ，必須先拆分
                Aggregation.lookup("JOB_PERMISSION", "JOB_ROLES.STAFF_PERMISSION_IDS.REF_ID","_id","JOB_ROLES.JOB_PERMISSION"),
                Aggregation.group("_id")
                        .first("STAFF_NAME").as("STAFF_NAME")
                        .first("STAFF_UID").as("STAFF_UID")
                        .first("STAFF_SEX").as("STAFF_SEX")
                        .first("STAFF_EMAIL").as("STAFF_EMAIL")
                        .first("STAFF_PHONE").as("STAFF_PHONE")
                        .first("STAFF_TEL").as("STAFF_TEL")
                        .first("STAFF_ADD").as("STAFF_ADD")
                        .first("STAFF_AC").as("STAFF_AC")
                        .first("STAFF_PW").as("STAFF_PW")
                        .first("STAFF_STATUS").as("STAFF_STATUS")
                        .first("STAFF_POSI").as("STAFF_POSI")
                        .first("STAFF_BIRTH").as("STAFF_BIRTH")
                        .push("JOB_ROLES").as("JOB_ROLES")
        );
        AggregationResults<UnwindedStaff> aggregate = mongoTemplate.aggregate(aggregation, UnwindedStaff.class);
        return aggregate.getUniqueMappedResult();
    }

    @Override
    public UnwindedStaff findByStaffAc(String staffAc) {
        TypedAggregation<Staff> aggregation = Aggregation.newAggregation(
                Staff.class,
                Aggregation.match(Criteria.where("STAFF_AC").is(staffAc)),
                Aggregation.lookup("JOB_ROLE", "STAFF_ROLE_IDS.REF_ID", "_id", "JOB_ROLES"),
                Aggregation.unwind("JOB_ROLES"), // 不能進行 nested look up ，必須先拆分
                Aggregation.lookup("JOB_PERMISSION", "JOB_ROLES.STAFF_PERMISSION_IDS.REF_ID","_id","JOB_ROLES.JOB_PERMISSION"),
                Aggregation.group("_id")
                        .first("STAFF_NAME").as("STAFF_NAME")
                        .first("STAFF_UID").as("STAFF_UID")
                        .first("STAFF_SEX").as("STAFF_SEX")
                        .first("STAFF_EMAIL").as("STAFF_EMAIL")
                        .first("STAFF_PHONE").as("STAFF_PHONE")
                        .first("STAFF_TEL").as("STAFF_TEL")
                        .first("STAFF_ADD").as("STAFF_ADD")
                        .first("STAFF_AC").as("STAFF_AC")
                        .first("STAFF_PW").as("STAFF_PW")
                        .first("STAFF_STATUS").as("STAFF_STATUS")
                        .first("STAFF_POSI").as("STAFF_POSI")
                        .first("STAFF_BIRTH").as("STAFF_BIRTH")
                        .push("JOB_ROLES").as("JOB_ROLES")
        );
        AggregationResults<UnwindedStaff> aggregate = mongoTemplate.aggregate(aggregation, UnwindedStaff.class);
        return aggregate.getUniqueMappedResult();
    }

    @Override
    public UnwindedStaff findByStaffAcAndStaffPw(String staffAc, String staffPw) {
        TypedAggregation<Staff> aggregation = Aggregation.newAggregation(
                Staff.class,
                Aggregation.match(Criteria.where("STAFF_AC").is(staffAc)),
                Aggregation.match(Criteria.where("STAFF_PW").is(staffPw)),
                Aggregation.lookup("JOB_ROLE", "STAFF_ROLE_IDS.REF_ID", "_id", "JOB_ROLES"),
                Aggregation.unwind("JOB_ROLES"), // 不能進行 nested look up ，必須先拆分
                Aggregation.lookup("JOB_PERMISSION", "JOB_ROLES.STAFF_PERMISSION_IDS.REF_ID","_id","JOB_ROLES.JOB_PERMISSION"),
                Aggregation.group("_id")
                        .first("STAFF_NAME").as("STAFF_NAME")
                        .first("STAFF_UID").as("STAFF_UID")
                        .first("STAFF_SEX").as("STAFF_SEX")
                        .first("STAFF_EMAIL").as("STAFF_EMAIL")
                        .first("STAFF_PHONE").as("STAFF_PHONE")
                        .first("STAFF_TEL").as("STAFF_TEL")
                        .first("STAFF_ADD").as("STAFF_ADD")
                        .first("STAFF_AC").as("STAFF_AC")
                        .first("STAFF_PW").as("STAFF_PW")
                        .first("STAFF_STATUS").as("STAFF_STATUS")
                        .first("STAFF_POSI").as("STAFF_POSI")
                        .first("STAFF_BIRTH").as("STAFF_BIRTH")
                        .push("JOB_ROLES").as("JOB_ROLES")
        );
        AggregationResults<UnwindedStaff> aggregate = mongoTemplate.aggregate(aggregation, UnwindedStaff.class);
        return aggregate.getUniqueMappedResult();
    }
}

