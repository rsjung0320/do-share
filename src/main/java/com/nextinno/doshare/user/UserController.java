package com.nextinno.doshare.user;

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

import com.nextinno.doshare.api.API;
import com.nextinno.doshare.domain.users.User;
import com.nextinno.doshare.domain.users.UserRepository;

/**
 * @author rsjung
 *
 */
@Controller
@RequestMapping(API.USER)
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> findByEmail(@RequestBody final User reqUser) {
        User user = userRepository.findByEmail(reqUser.getEmail());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
