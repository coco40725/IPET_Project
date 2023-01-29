package com.ipet.web.member.repositories;

import com.ipet.web.member.entities.Pet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-上午 10:01
 */
@Repository
public interface CustomPetRepository {
    Pet findById(String id);
    List<Pet> findAll();

}
