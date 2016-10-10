package com.nextinno.doshare.login;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextinno.doshare.api.API;
import com.nextinno.doshare.common.Common;
import com.nextinno.doshare.domain.users.User;
import com.nextinno.doshare.login.mapper.LoginMapper;

/**
 * @author rsjung
 */
@Controller
@RequestMapping(API.LOGIN)
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private LoginMapper loginMapper;
    
    @RequestMapping(value = "signin", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<String> singin(@RequestBody final User reqUser)
        throws ServletException {
        
        // to-do 암호화 하는 것 생각해 보기, 클라이언트에서 먼저 암호화 해서 보내고, 받고 해야 할 것 같다.
        String password = reqUser.getPassword();
        
        // 1. DB에서 값을 가져온다.
        User user = certificationUser(reqUser);
        if( user != null ){
            
            // 2. password를 비교한다.
            if(password.equals(user.getPassword())){
                
                // 3. 아무런 변경이 없으면 token을 발행한다.
                String token = Common.generateToken(user.getEmail(), user.getRole());
                
                return new ResponseEntity<String>(token, HttpStatus.OK);
            } else {
                
                return new ResponseEntity<String>("Check your ID or Password.", HttpStatus.BAD_REQUEST);
            }
        } else {
            // 그리고 i18n을 적용하도록 한다. 
            return new ResponseEntity<String>("Check your ID or Password.", HttpStatus.BAD_REQUEST);
        }
    }
    
    // to-do 토큰 발행해 주는 것을 만든다.
    // 먼저 해당 id와  증명된 사용자라면 다면 다시 토큰을 재 발행해 준다.
    

    /**
     * @param reqUser
     * @return 201 성공적으로 요청되었으며 서버가 새 리소스를 작성했다.
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity signup(@RequestBody final User reqUser)
        throws Exception {
        
        // 1. DB에 값이 있는지 확인한다.
        if(!isExistUser(reqUser)){
            // 2. 없으면 DB에 값을 insert 한다.
            loginMapper.addUser(reqUser);
        } else {
            // 2.1 있으면 에러메시지를 response 한다.
            // to-do i18n 처리한다.
            logger.error("User already existed.");
            return new ResponseEntity<String>("User already existed.", HttpStatus.BAD_REQUEST);
        }
        
        // 3. 201 ok를 준다.
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * @param reqUser
     * @return
     */
    private User certificationUser(User reqUser) {
        User user = loginMapper.certificationUser(reqUser);
        if( user != null ){
            return user;
        } else {
            return null;
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
