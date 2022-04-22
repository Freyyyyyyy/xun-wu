package com.cpt202.xunwu.controller;

import javax.servlet.http.HttpSession;

import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {
    
    @Autowired
    private UserService userService;

    @PostMapping(value="login")
    public ComResult login(@RequestParam(name = "userName") String userName,
                           @RequestParam(name = "password") String password,
                           HttpSession session) {
                       
        return userService.login(userName, password, session);
    }

    //reserved for forget password

}
