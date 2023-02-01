package com.ipet.web.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 10:36
 */
@Controller
public class LoginController {
    @GetMapping({"/ipet-back/login","/ipet-back/logout"})
    public String login(){
        return "backstage/login";
    }
}
