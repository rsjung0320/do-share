package com.nextinno.doshare.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextinno.doshare.board.BoardDto;
import com.nextinno.doshare.main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rsjung on 2016-11-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@Transactional // @Test로 붙은 것들을 한번 실행 시킨 후 rollback을 한다.
public class UserControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    public void findByEmail() throws Exception {

        User user = new User();
        user.setEmail("junittest@test.com");
        user.setPassword("qwer12");
        user.setName("junit4");
        user.setRole("user");

        ResultActions result = mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        result.andDo(print());
        result.andExpect(status().isOk());
//        result.andExpect(jsonPath("$.title", is("test")));
    }

}