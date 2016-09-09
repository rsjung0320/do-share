/*
 * Copyright (C) 2016, Nable Communications, Inc. All rights reserved.
 * 
 * This software is confidential and proprietary information of Nable Communications, Inc.
 */
package com.nextinno.doshare.login;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextinno.doshare.domain.users.User;
import com.nextinno.doshare.login.mapper.LoginMapper;

/**
 * @author nelim
 */
@Controller
@RequestMapping("/test")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
//    @Autowired
//    private LoginMapper loginMapper;
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ResponseEntity<List<User>> listAllAdmins() {
//        List<User> users = loginMapper.findByUserName();
//        logger.info("users : {}", users);
//        
//        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//    }
    
    private final Map<String, List<String>> userDb = new HashMap<>();

    public LoginController() {
        userDb.put("tom", Arrays.asList("user"));
        userDb.put("sally", Arrays.asList("user", "admin"));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody final UserLogin login)
        throws ServletException {
        if (login.name == null || !userDb.containsKey(login.name)) {
            throw new ServletException("Invalid login");
        }
        
        LoginResponse test = new LoginResponse(Jwts.builder().setSubject(login.name)
                .claim("roles", userDb.get(login.name)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "nextinno").compact());
        
        String aa = test.token;
        return aa;
    }

    @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}
