package com.cpt202.xunwu.service;

import java.util.Random;
import javax.servlet.http.HttpSession;

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
            result.setMessage("验证码已发送");
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
                result.setMessage("登录成功");
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


}
