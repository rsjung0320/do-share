/*
 * Copyright (C) 2016, Nable Communications, Inc. All rights reserved.
 * 
 * This software is confidential and proprietary information of Nable Communications, Inc.
 */
package com.nextinno.doshare.login;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nextinno.doshare.domain.users.User;
import com.nextinno.doshare.login.mapper.LoginMapper;

/**
 * @author nelim
 */
@RestController
public class SampleRestController {
    private final Logger logger = LoggerFactory.getLogger(SampleRestController.class);
    
    @Autowired
    private LoginMapper loginMapper;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllAdmins() {
        List<User> users = loginMapper.findByUserName();
        logger.info("users : {}", users);
        
        return null;
    }
}
