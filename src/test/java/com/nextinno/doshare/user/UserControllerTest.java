package com.nextinno.doshare.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextinno.doshare.main.Main;
import com.nextinno.doshare.user.UserDto.CreateUser;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rsjung on 2016-11-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration // 이 Annotation을 붙이지 않으면 WebApplicationContext 는 ApplicationContext가 된다고 함
@Transactional // @Test로 붙은 것들을 한번 실행 시킨 후 rollback을 한다.
public class UserControllerTest {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    public void findByEmail() throws Exception {
        signup_사용자추가();

        User user = new User();
        user.setEmail("junittest@test.com");
        user.setPassword("qwer12");
        user.setName("test");
        user.setRole("USER");

        ResultActions result = mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        result.andDo(print());
        result.andExpect(status().isOk());
//        result.andExpect(jsonPath("$.title", is("test")));
    }

    private CreateUser getCreateUser() {
        CreateUser user = new CreateUser();
        user.setEmail("junittest@test.com");
        user.setPassword("qwer12");
        user.setName("test");
        user.setRole("USER");
        return user;
    }

    private void signup_사용자추가() throws Exception {
        CreateUser user = getCreateUser();

        ResultActions result = mockMvc.perform(post("/login/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.email", is("junittest@test.com")));
    }
}