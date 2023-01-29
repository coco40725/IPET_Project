package temp.member.services;

import com.ipet.web.member.entities.Member;
import com.ipet.web.member.repositories.MemberRepository;
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

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
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
        Member oldMember = memberRepository.findById(member.getMemId()).orElse(null);
        if (oldMember == null){
            return "查無此會員";
        }else{
            // 不可更改: 帳號
            if (!oldMember.getMemAc().equals(member.getMemAc())) {
                return "不可更改帳號";
            }
            // 不可更改: 身分證
            if (!oldMember.getMemUid().equals(member.getMemUid())){
                return "不可更改身分證";
            }

            memberRepository.save(member);
            return "success";
        }
    }


    // member query
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
    public Member getMemberById(Integer id){
        return memberRepository.findById(id).orElse(null);
    }
}
