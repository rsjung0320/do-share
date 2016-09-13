package com.nextinno.doshare.login;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextinno.doshare.api.API;
import com.nextinno.doshare.domain.users.User;
import com.nextinno.doshare.login.mapper.LoginMapper;

import java.util.Arrays;
import java.util.Date;

/**
 * @author rsjung
 */
@Controller
@RequestMapping(API.LOGIN)
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private LoginMapper loginMapper;

    private final Map<String, List<String>> userDb = new HashMap<>();

    public LoginController() {
        userDb.put("tom", Arrays.asList("user"));
        userDb.put("sally", Arrays.asList("user", "admin"));
    }

    @RequestMapping(value = "signin", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String singin(@RequestBody final User reqUser)
        throws ServletException {
        
        // to-do 암호화 하는 것 생각해 보기, 클라이언트에서 먼저 암호화 해서 보내고, 받고 해야 할 것 같다.
        String email = reqUser.getEmail();
        String role = reqUser.getRole();

        if(certificationUser(reqUser)){
            String token = Jwts.builder().setSubject(email)
                    .claim("roles", role).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "nextinno").compact();
            
            return token;
        } else {
            // to-do exception으로 하지 말고, response를 error코드와 에러메지시를 주도록 한다.
            // 아니면 에러 코드만 주고 클라이언트에서 하도록 한다. 
            // 그리고 i18n을 적용하도록 한다. 
            throw new ServletException("아이디, 패스워드가 다릅니다. 다시확인해주세요.");
        }
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String signup(@RequestBody final User reqUser)
        throws Exception {
        
        String email = reqUser.getEmail();
        String role = reqUser.getRole();
        
        // 1. DB에 값이 있는지 확인한다.
        if(!isExistUser(reqUser)){
            // 2. 없으면 DB에 값을 insert 한다.
            loginMapper.addUser(reqUser);
            // to-do 에러처리 해야 함
        } else {
            // 2.1 있으면 에러메시지를 response 한다.
            logger.error("user가 존재한다.");
            throw new Exception("user가 존재한다.");
        }
        
        // 3. insert 후 jwt 를 만들어서 200 ok와 token 값을 준다.
        String token = Jwts.builder().setSubject(email)
                .claim("roles", role).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "nextinno").compact();
        
        return token;
    }

    /**
     * @param reqUser
     * @return
     */
    private boolean certificationUser(User reqUser) {
        User user = loginMapper.certificationUser(reqUser);
        if( user != null ){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * @param reqUser
     * @return
     */
    private boolean isExistUser(User reqUser) {
        User user = loginMapper.findByUser(reqUser);
        if( user != null ){
            return true;
        } else {
            return false;
        }
    }
}
