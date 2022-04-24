package com.cpt202.xunwu.service;

import java.util.Random;
import javax.servlet.http.HttpSession;

import com.cpt202.xunwu.bean.Changepassword;
import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.model.UserInfo;
import com.cpt202.xunwu.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public ComResult register(String authCode, UserInfo userInfo, HttpSession session) {
        ComResult result = new ComResult();
        try {
            String code = (String) session.getAttribute("authCode");
            if (!code.equals(authCode)){
                result.setCode(422);
                result.setMessage("验证码错误");
                return result;
            }

            String verifyEmail = (String) session.getAttribute("email");
            if(!verifyEmail.equals(userInfo.getUserEmail())){
                result.setCode(422);
                result.setMessage("邮箱地址改变，请使用验证邮箱");
                return result;
            }

            String userName = userInfo.getUserName();

            //reserved for check masked word

            UserInfo existUser = userRepo.findUserByUserName(userName);
            if(existUser != null){
                result.setCode(422);
                result.setMessage("用户名已存在");
            }else{
                userRepo.save(userInfo);
                result.setCode(200);
                result.setMessage("注册成功");
            }
            return result;

        } catch (Exception e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
            return result;
        }
    }


    public ComResult sendEmail(String email, HttpSession session) {
        ComResult result = new ComResult<>();
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("XunWu验证码");
            String code = generateAuthCode();
            
            session.setAttribute("authCode",code);
            session.setAttribute("email",email);
            session.setMaxInactiveInterval(120);

            mailMessage.setText("XunWu验证码："+code);
            mailMessage.setTo(email);
            mailMessage.setFrom(from);
            mailSender.send(mailMessage);

            result.setCode(200);
            result.setMessage("验证码已发送，有效时间两分钟");
            return result;

        }catch (Exception e){
            result.setMessage(e.getMessage());
            e.printStackTrace();
            return result;
        }
    }


    public String generateAuthCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


    public ComResult login(String userName, String password, HttpSession session){
        ComResult result = new ComResult();
        try{
            UserInfo user = userRepo.findUserByUserName(userName);
            
            if(user == null){
                result.setCode(422);
                result.setMessage("用户名不存在");

            }else if(!user.getUserPassword().equals(password)){
                result.setCode(422);
                result.setMessage("密码错误");

            }else{
                result.setCode(200);
                result.setMessage("登录成功!"+userName);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userName", user.getUserName());
            }

            return result;

        } catch (Exception e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
            return result;
        }
    }


    //reserved for forget password
    // forget PassWord
    public ComResult forgetPassWord(String email, String authCode, UserInfo userInfo, Changepassword changepassword,
    HttpSession session) {
    ComResult result = new ComResult();
    UserInfo exist_email = userRepo.findUserByuserEmail(email);
    if (checkAuthcodeAndEmail(authCode, session, userInfo, email, result).getMessage() != null) {
        return result;
    } else {
        exist_email.setUserPassword(changepassword.getChangepassword());
        userRepo.save(exist_email);
        result.setMessage("修改成功");
    }
    return result;
    }

    // check authcode and email
    public ComResult checkAuthcodeAndEmail(String authCode, HttpSession session, UserInfo userInfo, String email,
    ComResult result) {
    if (authCode.equals("")) {
        result.setMessage("请输入验证码！");
    } else {
        String code = (String) session.getAttribute("code");

        if (code == null) {
            result.setMessage("请用邮件发送验证码 :" + (String) session.getAttribute("code") + "    "
                    + (String) session.getAttribute("email"));
            return result;
        }

        if (!code.equals(authCode)) {
            result.setCode(422);
            result.setMessage("验证码错误");
            // result.setData(null);
        } else {
            // check email(it is used to get code)
            String GetCodeEmail = (String) session.getAttribute("email");
            String userInfoEmail = userInfo.getUserEmail();
            if (email.equals("")) {
                if (!GetCodeEmail.equals(userInfoEmail))
                    result.setMessage("请填写刚接收验证码的邮箱!");
            } else {
                if (!GetCodeEmail.equals(email))
                    result.setMessage("请填写刚接收验证码的邮箱!");
            }
        }
    }
    return result;
    }

        // send email
        public ComResult sendEmailB(String email, HttpSession session, String way) {
            ComResult result = new ComResult<>();
    
            try {
                if (email.equals("")) {
                    result.setMessage("请填写邮箱！");
                    return result;
                }
                UserInfo existEmail = userRepo.findUserByuserEmail(email);
                // sendEmail in forgerPassword
                if (way.equals("forgerPassord")) {
                    if (existEmail == null) {
                        result.setMessage("该邮箱未被注册!");
                        return result;
                    }
                    // sendEmail in register
                } else {
                    if (existEmail != null) {
                        result.setMessage("该邮箱已被注册!");
                        return result;
                    }
                }
                SimpleMailMessage mailMessage = new SimpleMailMessage();
    
                mailMessage.setSubject("XunWu验证码");
    
                String code = generateAuthCode();
    
                session.setAttribute("email", email);
                session.setAttribute("code", code);
                session.setMaxInactiveInterval(120);
    
                mailMessage.setText("XunWu验证码：" + code);
                mailMessage.setTo(email);
                mailMessage.setFrom(from);
                mailSender.send(mailMessage);
                result.setCode(200);
                result.setMessage("验证码已发送");
    
                return result;
    
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
        }


}
