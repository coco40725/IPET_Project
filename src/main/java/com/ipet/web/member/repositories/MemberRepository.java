package com.ipet.web.member.repositories;

import com.ipet.web.member.entities.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 12:53
 */
@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
    Member findByMemAc(String ac);
    Member findByMemUid(String uid);
    Member findByPetContaining(String petId);
}
