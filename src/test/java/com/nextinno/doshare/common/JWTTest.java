//package com.nextinno.doshare.common;
//
//import static org.junit.Assert.*;
//
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.security.SignatureException;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.auth0.jwt.JWTVerifyException;
//
///**
// * @author rsjung
// *
// */
//public class JWTTest {
//
//    NiJWTComponent niJWTComponent; 
//    
//    @Before
//    public void setup(){
//        niJWTComponent = new NiJWTComponent();
//    }
//    
//    @Test
//    public void JWT_Sign_테스트(){
//        String jwt = niJWTComponent.signJWT("rsjung@nablecomm.com");
//        String[] temp = jwt.split("\\.");
//        assertEquals(3, temp.length);
//    }
//    
//    @Test
//    public void JWT_Verify_테스트(){
//        String jwt = niJWTComponent.signJWT("rsjung@nablecomm.com");
//        boolean result = false;
//        try {
//            result = niJWTComponent.verifyJWT(jwt);
//        } catch (InvalidKeyException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalStateException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SignatureException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (JWTVerifyException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        assertEquals(result, true);
//    }
//}
