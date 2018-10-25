package com.ygb.controller;

import com.sun.corba.se.spi.legacy.interceptor.UnknownType;
import com.ygb.jpa.UserJPA;
import com.ygb.jpa.UserPasswordJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Optional;

//@RestController // 声明一个访问控制器
@Controller
public class LoginController {
    @Autowired
    private UserPasswordJPA userPasswordJPA;
    @RequestMapping(value = "/login")
//    public String login(){
//        return "/interface.html";
//    }
    public @ResponseBody boolean login(HttpServletRequest request){ // 使用@ResponseBody，返回值不会跳转路径
        String name = request.getParameter("username");
        String pasd = request.getParameter("password");
       // userPasswordJPA.findByUserAndPassword(name, pasd);

        //UserEntity contact = contactsRepository.findByName(name);
        return userPasswordJPA.existsByUserAndPassword(name, pasd);
    }
}
