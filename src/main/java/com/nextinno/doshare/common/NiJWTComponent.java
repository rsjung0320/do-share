///*
// * Copyright (C) 2016, Nable Communications, Inc.
// * All rights reserved.
// * 
// * This software is confidential and proprietary information
// * of Nable Communications, Inc.
// */
//package com.nextinno.doshare.common;
//
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.security.SignatureException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.auth0.jwt.JWTSigner;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.JWTVerifyException;
//
///**
// * @author rsjung
// *
// */
//@Component
//public class NiJWTComponent {
//    private final Logger logger = LoggerFactory.getLogger(NiJWTComponent.class);
//    
//    public NiJWTComponent(){
//        logger.info("NiJWTComponent Start!!");;
//    }
//    
//    /**
//     * 암호화 하는 함수 [SHA256]
//     * 
//     * @param user
//     * @return
//     */
//    public String signJWT(String user){
//        final String issuer = user;
//        final String secret = "{{nextinno}}";
//
//        final long iat = System.currentTimeMillis() / 1000l; // issued at claim 
//        final long exp = iat + 60L; // expires claim. In this case the token expires in 60 seconds
//
//        final JWTSigner signer = new JWTSigner(secret);
//        final HashMap<String, Object> claims = new HashMap<String, Object>();
//        claims.put("iss", issuer);
//        claims.put("exp", exp);
//        claims.put("iat", iat);
//
//        final String jwt = signer.sign(claims);
//        
//        return jwt;
//    }
//    
//    public boolean verifyJWT(String jwt) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException, JWTVerifyException{
//        boolean result = false;
//        
//        final String secret = "{{nextinno}}";
//        
//        try {
//            final JWTVerifier jwtVerifier = new JWTVerifier(secret);
//            final Map<String,Object> claims= jwtVerifier.verify(jwt);
//            // 1. 만료된 사람인지 확인한다.
//            
//            // 2. 인증이 된 사람은 exp, iat를 연장해주는 로직을 추가하고, 다시 보내준다.
//            
//            logger.info("claims : " + claims);
//            
//            result = true;
//        } catch (JWTVerifyException e) {
//            // Invalid Token
//            throw e;
//        }
//        return result;
//    }
//}
