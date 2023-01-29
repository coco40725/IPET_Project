package com.ipet.web.member.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipet.web.member.entities.Member;
import com.ipet.web.member.services.MemberServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/ipet-back/member")
    public String addMember(@RequestBody Map<String, String> map) {
        return null;
    }

    // member edit
    @PutMapping("/ipet-back/member")
    @ResponseBody
    public String editMember(@RequestBody Map<String, String> map) {
        System.out.println(map.get("jsonFile"));
        Gson gson = builder.serializeNulls().create();
        Member member = gson.fromJson(map.get("jsonFile"), Member.class);
        System.out.println(member);
        return memberServices.editMember(member);
    }

    // member query
    @GetMapping("/ipet-back/members")
    public String getAllMembers(Model model) {
        model.addAttribute("members", memberServices.getAllMembers());
        return "backstage/member/memberList";
    }

    @GetMapping("/ipet-back/member/{id}")
    @ResponseBody
    public String getMembersById(@PathVariable("id") Integer id) {
        Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(memberServices.getMemberById(id));
    }
}
