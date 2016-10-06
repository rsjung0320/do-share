package com.nextinno.doshare.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nextinno.doshare.config.DoShareConfig;

/**
 * @author rsjung
 *
 */
@Component
public class Common {
    private static final Logger logger = LoggerFactory.getLogger(Common.class);
    
    public Common(){
        logger.info("Common Start.!");
    }
    
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey("nextInno")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    
    /**
     * 토큰을 생성하는 function
     * @param email
     * @param role
     * @return
     */
    public static String generateToken(String email, String role) {
        return Jwts.builder().setSubject(email)
                .claim("roles", role).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, DoShareConfig.SECRET).compact();
    }
}
    
    
