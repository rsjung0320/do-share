package com.nextinno.doshare.main;

import java.nio.charset.Charset;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.http.converter.StringHttpMessageConverter;

import javax.servlet.Filter;

import com.nextinno.doshare.config.DoShareConfig;

@Controller
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceTransactionManagerAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = DoShareConfig.DEFAULT_BASE_PACKAGE)
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
//    /**
//     * @return
//     */
    
    @GetMapping("/user")
    // 나는 json으로만 데이터를 주고 받을 것이니 @ResponseBody는 없도록 한다.
    @ResponseBody
    String home(HttpServletRequest request) {
        // 처음으로 들어오면 claims는 String 토큰으로 넘어온다.\
        // 하지만 한번 인증 받는 것은 claims가 object로 들어오니 현재 에러가 나는 것이다.
        final String token = (String) request.getAttribute("claims");
//        String username = getUsernameFromToken(token);
        
        // 토큰을 가져와서 먼저 리플래시해야하는지 보고 해야하면 해서 return 하고, 안해도 되면 그냥 값을 리턴한다.
        // 아래 주석은 참고해서 만들어본다.
//        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
//
//        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
//            String refreshedToken = jwtTokenUtil.refreshToken(token);
//            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
//        } else {
//            return ResponseEntity.badRequest().body(null);
//        }
        
        logger.info("home API Request!");
//        claims.
        return token;
    }
    
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey("nextinno")
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
//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new JwtFilter());
//        registrationBean.addUrlPatterns("/api/**");
//
//        return registrationBean;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        
    }
    
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
 
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
