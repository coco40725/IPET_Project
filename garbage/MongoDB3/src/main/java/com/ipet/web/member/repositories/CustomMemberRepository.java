package com.ipet.web.member.repositories;

import com.ipet.web.member.entities.unwinded.UnwindedMember;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-下午 12:06
 */
public interface CustomMemberRepository {
    List<UnwindedMember> findAll();
    UnwindedMember findById(String id);

}
