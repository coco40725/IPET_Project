package com.ipet.web.member.repositories;

import com.ipet.web.member.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 12:53
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByMemAc(String ac);
    Member findByMemUid(String uid);
}
