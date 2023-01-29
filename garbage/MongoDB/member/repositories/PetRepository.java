package com.ipet.web.member.repositories;

import com.ipet.web.member.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 12:53
 */
@Repository
public interface PetRepository extends MongoRepository<Pet, String> {
}
