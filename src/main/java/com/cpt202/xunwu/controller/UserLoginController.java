package com.cpt202.xunwu.controller;

import javax.servlet.http.HttpSession;

import com.cpt202.xunwu.bean.Changepassword;
import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.model.UserInfo;
import com.cpt202.xunwu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        // sendEmail in the forgerPassord
        @PostMapping(value = "verifyB")
        public ComResult verifyB(HttpSession session, @RequestParam(name = "email") String email) {
            String way = "forgerPassord";
            return userService.sendEmailB(email, session, way);
        }
    
        // forget password
        @PostMapping(value = "forgerPassord")
        public ComResult forgerpassword(@RequestParam(name = "email") String email,
                @RequestParam(name = "authCode") String authCode,
                @RequestBody Changepassword changepassword,
                UserInfo userInfo,
                HttpSession session) {
            return userService.forgetPassWord(email, authCode, userInfo, changepassword, session);
        }

}
