package com.ipet.web.member.services;

import com.ipet.web.member.entities.Member;
import com.ipet.web.member.entities.unwinded.UnwindedMember;
import com.ipet.web.member.repositories.CustomMemberRepository;
import com.ipet.web.member.repositories.MemberRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-25-下午 03:57
 */
@Service
public class MemberServices {
    private MemberRepository memberRepository;
    private CustomMemberRepository customMemberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setCustomMemberRepository(CustomMemberRepository customMemberRepository){
        this.customMemberRepository = customMemberRepository;
    }

    // member add
    @Transactional
    public String addMember(Member member){
        // 帳號相同
        if (memberRepository.findByMemAc(member.getMemAc()) != null) {
            return "該帳號已被註冊";
        }
        // 身分證相同
        if (memberRepository.findByMemUid(member.getMemUid()) != null){
            return "該身分證已被使用";
        }
        memberRepository.save(member);
        return "success";
    }



    // member edit
    @Transactional
    public String editMember(Member member){
        Member oldMember = memberRepository.findById(member.getId()).orElse(null);
        if (oldMember == null){
            return "查無此會員";
        }else{
            // 不可更改: 帳號
            if (member.getMemAc() != null) {
                return "不可更改帳號";
            }
            // 不可更改: 身分證
            if (member.getMemUid() != null){
                return "不可更改身分證";
            }
            customMemberRepository.partialUpdate(member);
            return "success";
        }
    }


    // member query
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
    public Member getMemberById(String id){
        return memberRepository.findById(id).orElse(null);
    }

    public Member getMemByPetId(String id){
        return memberRepository.findByPetContaining(new ObjectId(id));
    }

    public List<UnwindedMember> getAllUnwindedMembers(){
        return customMemberRepository.findAll();
    }

}
