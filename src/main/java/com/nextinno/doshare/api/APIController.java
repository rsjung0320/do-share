//package com.nextinno.doshare.api;
//
//import io.jsonwebtoken.Claims;
//
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * @author rsjung
// *
// */
//@Controller
//@RequestMapping("/api")
//public class APIController {
//    private static final Logger logger = LoggerFactory.getLogger(APIController.class);
//    
//    @SuppressWarnings("unchecked")
//    @RequestMapping(value = "role/{role}", method = RequestMethod.GET)
//    @ResponseBody
//    public Boolean login(@PathVariable final String role,
//            final HttpServletRequest request) throws ServletException {
//        logger.info("login API Request!");
//        final Claims claims = (Claims) request.getAttribute("claims");
//
//        return ((List<String>) claims.get("roles")).contains(role);
//    }
//    
//    @GetMapping("/user")
//    // 나는 json으로만 데이터를 주고 받을 것이니 @ResponseBody는 없도록 한다.
//    @ResponseBody
//    String home() {
//        logger.info("user API Request!");
//        return "connect/login";
//    }
//    
//    @GetMapping("/user2")
//    // 나는 json으로만 데이터를 주고 받을 것이니 @ResponseBody는 없도록 한다.
//    @ResponseBody
//    String user2() {
//        logger.info("user2 API Request!");
//        return "123123123";
//    }
//}