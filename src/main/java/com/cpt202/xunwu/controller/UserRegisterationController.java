package com.cpt202.xunwu.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.model.UserInfo;
import com.cpt202.xunwu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RestController
public class UserRegisterationController {
    
    @Autowired
    private UserService userService;

    @PostMapping(value="register")
    public ComResult register(@RequestParam(name = "authCode") String authCode,
                              @RequestBody UserInfo userInfo,
                              HttpSession session) {
        
        return userService.register(authCode,userInfo,session);
    }

    
    @PostMapping(value="verify")
    public ComResult verify(HttpSession session, @RequestParam(name = "email") String email) {
        
        return userService.sendEmail(email, session);
    }

}
