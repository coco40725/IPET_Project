package com.ipet.web.member.repositories;

import com.ipet.web.member.entities.Member;
import com.ipet.web.member.entities.Pet;

/**
 * @author Yu-Jing
 * @create 2023-02-01-下午 04:54
 */
public interface CustomPetRepository {
    void partialUpdate(Pet pet);
}
