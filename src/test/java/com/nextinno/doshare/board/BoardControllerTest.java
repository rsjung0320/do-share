package com.nextinno.doshare.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextinno.doshare.comment.CommentRepository;
import com.nextinno.doshare.main.Main;
import com.nextinno.doshare.user.User;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rsjung on 2016-11-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@Transactional // @Test로 붙은 것들을 한번 실행 시킨 후 rollback을 한다.
public class BoardControllerTest {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

//    @Test
//    public void uploadImage() throws Exception {
//
//    }

    @Test
    public void uploadBoard() throws Exception {
        // 1. User를 만든다.
//        User user = new User();
//        user.setEmail("test@nablecomm.com");
//        user.setName("test");
//        user.setPassword("test12");


        // 2. Board를 만든다.
        BoardDto.CreateBoard vo = new BoardDto.CreateBoard();
        vo.setTitle("test");
        vo.setEmail("test@nablecomm.com");
        vo.setContent("this is test content");
        vo.setUserIdx((long) 1);
        vo.setTags("Java, Spring");

        ResultActions result = mockMvc.perform(post("/api/v1/board/upload/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vo)));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.title", is("test")));
    }

    //    @Test
//    public void uploadEditedBoard() throws Exception {
//
//    }
//
//    @Test
//    public void deleteBoard() throws Exception {
//
//    }
//
//    @Test
//    public void download() throws Exception {
//
//    }
//
//    @Test
//    public void findAllBoard() throws Exception {
//
//    }
//
    @Test
    public void findAllBoardPage() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/board/all?size=3&page=1"));

        result.andDo(print());
        result.andExpect(status().isOk());
    }
//    @Test
//    public void findByIdBoard() throws Exception {
//
//    }

    @Test
    public void addComment() throws Exception {
        // Board를 먼저 만든다.
        // 만들어진 Board의 idx를 알아내고, url에 붙인다.
        ResultActions result = mockMvc.perform(get("/api/v1/board/comment/1"));

        result.andDo(print());
        result.andExpect(status().isOk());
    }

//    @Test
//    public void commentFindById() throws Exception {
//
//    }
//
//    @Test
//    public void saveFile() throws Exception {
//
//    }

}