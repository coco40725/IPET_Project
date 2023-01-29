package com.ipet.web.member.services;

import com.ipet.web.member.entities.Member;
import com.ipet.web.member.entities.Pet;
import com.ipet.web.member.repositories.MemberRepository;
import com.ipet.web.member.repositories.PetRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yu-Jing
 * @create 2023-01-25-下午 10:14
 */
@Service
public class PetServices {
    private PetRepository petRepository;
    private MemberRepository memberRepository;
    @Autowired
    public void setPetRepository(PetRepository petRepository){
        this.petRepository = petRepository;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // pet add
    @Transactional
    public String addPet(Pet pet){
        petRepository.save(pet);
        return "success";
    }
    // pet edit
    @Transactional
    public String editPet(Pet pet){
        Pet oldPet = petRepository.findById(pet.getId()).orElse(null);
        if (oldPet == null){
            return "查無此寵物";
        }
            petRepository.save(pet);
            return "success";
    }

    // pet query
    public List<Pet> getAllPets(){
        List<Pet> all = petRepository.findAll();
        for (Pet pet : all) {
            Member member = memberRepository.findByPetContaining(new ObjectId(pet.getId()));
            pet.setMem(member);
        }
        return all;
    }

    public Pet getPetById(String id){
        Pet pet = Objects.requireNonNull(petRepository.findById(id).orElse(null));
        Member member = memberRepository.findByPetContaining(new ObjectId(pet.getId()));
        pet.setMem(member);
        return pet;
    }

}
