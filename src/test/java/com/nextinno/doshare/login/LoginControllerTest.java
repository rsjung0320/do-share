package com.nextinno.doshare.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.nextinno.doshare.main.Main;
import com.nextinno.doshare.token.TokenDto;
import com.nextinno.doshare.user.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rsjung on 2016-11-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class) // @ContextConfiguration의 대안이다.
@WebAppConfiguration // 이 Annotation을 붙이지 않으면 WebApplicationContext 는 ApplicationContext가 된다고 함
@Transactional // @Test로 붙은 것들을 한번 실행 시킨 후 rollback을 한다.
public class LoginControllerTest {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;
    //

//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
//                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    public void signup() throws Exception {
        signup_사용자추가();
    }

    @Test
    public void signup_동일아이디로추가_에러400() throws Exception {
        signup_사용자추가();

        UserDto.CreateUser user = getCreateUser();

        ResultActions result = mockMvc.perform(post("/login/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        result.andDo(print());
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.code", is("already.exist.user.exception")));
    }

    @Test
    public void signin_notRemember() throws Exception {
        signup_사용자추가();

        getToken();
    }

    @Test
    public void signin_remember() throws Exception {
        signup_사용자추가();

        get_refreshToken();
    }

    @Test
    public void signin_패스워드오류() throws Exception {
        signup_사용자추가();

        UserDto.CreateUser user = getCreateUser();
        LoginDto.SignIn reqlogin = new LoginDto.SignIn();
        reqlogin.setEmail(user.getEmail());
        reqlogin.setPassword("failpassword");
        reqlogin.setRemember(false);

        ResultActions result = mockMvc.perform(post("/login/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqlogin)));

        result.andDo(print());
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.code", is("not.equal.password.exception")));
    }
    @Test
    public void refreshToken() throws Exception {
        signup_사용자추가();

        String refreshToken = get_refreshToken();

        UserDto.CreateUser user = getCreateUser();

        TokenDto.RefreshToken reqToken = new TokenDto.RefreshToken();
        reqToken.setRefreshToken(refreshToken);
        reqToken.setEmail(user.getEmail());
        reqToken.setRole(user.getRole());


        ResultActions result = mockMvc.perform(post("/login/token/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqToken)));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.refreshToken", notNullValue()));
    }

    @Test
    public void regenerateToken() throws Exception {
        signup_사용자추가();

        String token = getToken();

        UserDto.CreateUser user = getCreateUser();

        TokenDto.RegenerateToken reqToken = new TokenDto.RegenerateToken();
        reqToken.setToken(token);
        reqToken.setEmail(user.getEmail());
        reqToken.setRole(user.getRole());

        System.out.println("\n==================================");
        System.out.println(reqToken.toString());

        ResultActions result = mockMvc.perform(post("/login/token/regenerate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqToken)));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.token", notNullValue()));
    }

    @Test
    public void regenerateToken_remember() throws Exception {
        signup_사용자추가();

        String token = getToken();

        UserDto.CreateUser user = getCreateUser();

        TokenDto.RegenerateToken reqToken = new TokenDto.RegenerateToken();
        reqToken.setToken(token);
        reqToken.setEmail(user.getEmail());
        reqToken.setRole(user.getRole());
        reqToken.setRemember(true);

        System.out.println("\n==================================");
        System.out.println(reqToken.toString());

        ResultActions result = mockMvc.perform(post("/login/token/regenerate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqToken)));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.token", notNullValue()));
    }

    private String getToken() throws Exception {
        UserDto.CreateUser user = getCreateUser();
        LoginDto.SignIn reqlogin = new LoginDto.SignIn();
        reqlogin.setEmail(user.getEmail());
        reqlogin.setPassword(user.getPassword());
        reqlogin.setRemember(false);

        ResultActions result = mockMvc.perform(post("/login/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqlogin)));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.token", notNullValue()));
        result.andExpect(jsonPath("$.refreshToken", is("")));

        String content = result.andReturn().getResponse().getContentAsString();

        return JsonPath.read(content, "$.token");
    }

    private String get_refreshToken() throws Exception {
        UserDto.CreateUser user = getCreateUser();
        LoginDto.SignIn reqlogin = new LoginDto.SignIn();
        reqlogin.setEmail(user.getEmail());
        reqlogin.setPassword(user.getPassword());
        reqlogin.setRemember(true);

        ResultActions result = mockMvc.perform(post("/login/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqlogin)));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.token", notNullValue()));
        result.andExpect(jsonPath("$.refreshToken", notNullValue()));

        String content = result.andReturn().getResponse().getContentAsString();

        return JsonPath.read(content, "$.refreshToken");
    }

    private UserDto.CreateUser getCreateUser() {
        UserDto.CreateUser user = new UserDto.CreateUser();
        user.setEmail("junittest@test.com");
        user.setPassword("qwer12");
        user.setName("test");
        user.setRole("USER");
        return user;
    }

    private void signup_사용자추가() throws Exception {
        UserDto.CreateUser user = getCreateUser();

        ResultActions result = mockMvc.perform(post("/login/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.email", is("junittest@test.com")));
    }
}