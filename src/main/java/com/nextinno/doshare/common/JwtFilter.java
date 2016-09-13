package com.nextinno.doshare.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean {

    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    
    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final String secret = "nextinno";
        
        logger.info("=====================doFilter====================");
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            
//            throw new ServletException("Missing or invalid Authorization header.");
            LoginResponse test = new LoginResponse(Jwts.builder().setSubject("rsjung")
                    .claim("roles", "admin").setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "nextinno").compact());
            req.setAttribute("claims", test.token);
            
        } else {
            final String token = authHeader.substring(7); // The part after "Bearer "

            try {
                final Claims claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);
            }
            catch (final SignatureException e) {
                throw new ServletException("Invalid token.");
            }            
        }

        
        
        chain.doFilter(req, res);
    }
    
    @SuppressWarnings("unused")
    private class UserLogin {
        public String name;
        public String password;
    }

    private class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}
