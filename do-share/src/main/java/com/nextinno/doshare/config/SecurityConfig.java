///*
// * Copyright (C) 2016, Nable Communications, Inc.
// * All rights reserved.
// * 
// * This software is confidential and proprietary information
// * of Nable Communications, Inc.
// */
//package com.nextinno.doshare.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author rsjung
// *
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .formLogin()
//                .loginPage("/user/login")
//            .and()
//                .logout()
//                    .logoutUrl("/user/logout")
//                    .deleteCookies("JSESSIONID")
//                    .logoutSuccessUrl("/post/list")
//            .and()
//                .authorizeRequests()
//                    .antMatchers("/**/write*", "/**/edit*", "/**/delete*").authenticated()
//                    .antMatchers("/**").permitAll();
//    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/console/**");
//    }
//}
