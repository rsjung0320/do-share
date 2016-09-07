/*
 * Copyright (C) 2016, Nable Communications, Inc. All rights reserved.
 * 
 * This software is confidential and proprietary information of Nable Communications, Inc.
 */
package com.nextinno.doshare.dao;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nextinno.doshare.config.db.DoShareDbConfig;
import com.nextinno.doshare.domain.users.User;
import com.nextinno.doshare.login.mapper.LoginMapper;


/**
 * @author rsjung
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DoShareDbConfig.class })
public class MyBatisTest {
    private final Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

    @Autowired
    LoginMapper loginMapper;
    /**
     * @throws IOException
     */
    @Test
    public void gettingStarted() throws IOException {
        
        List<User> users = loginMapper.findByUserName();

        logger.info("users : ", users);
    }
}
