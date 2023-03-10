package com.ipet.web.member.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.member.entities.Member;
import com.ipet.web.member.services.MemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Yu-Jing
 * @create 2023-01-25-下午 03:54
 */
@Controller
public class MemberController {
    private MemberServices memberServices;
    private GsonBuilder builder;

    @Autowired
    public void setMemberServices(MemberServices memberServices) {
        this.memberServices = memberServices;
    }
    @Autowired
    public void setBuilder(GsonBuilder builder){
        this.builder = builder;
    }

    // member add
    @PreAuthorize("hasAnyAuthority('editMems')")
    @PostMapping("/ipet-back/member")
    public String addMember(@RequestBody Map<String, String> map) {
        return null;
    }

    // member edit
    @PreAuthorize("hasAnyAuthority('editMems')")
    @PatchMapping("/ipet-back/member/{id}")
    @ResponseBody
    public String editMember(@PathVariable("id") String id, @RequestBody Member member) {
        member.setId(id);
        return memberServices.editMember(member);
    }

    // member query
    @PreAuthorize("hasAnyAuthority('editMems','displayMems')")
    @GetMapping("/ipet-back/members")
    public String getAllMembers(Model model) {
        model.addAttribute("members", memberServices.getAllMembers());
        return "backstage/member/memberList";
    }

    @PreAuthorize("hasAnyAuthority('editMems','displayMems')")
    @GetMapping("/ipet-back/member/{id}")
    @ResponseBody
    public String getMembersById(@PathVariable("id") String id) {
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(memberServices.getMemberById(id));
    }
    @PreAuthorize("hasAnyAuthority('editMems','displayMems')")
    @GetMapping("/ipet-back/memberPets")
    @ResponseBody
    public String getAllMembersPets() {
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson( memberServices.getAllUnwindedMembers());
    }

}
