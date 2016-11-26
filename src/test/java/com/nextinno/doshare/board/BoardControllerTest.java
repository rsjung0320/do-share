package com.nextinno.doshare.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.nextinno.doshare.comment.Comment;
import com.nextinno.doshare.comment.CommentDto;
import com.nextinno.doshare.main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rsjung on 2016-11-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration // 이 Annotation을 붙이지 않으면 WebApplicationContext 는 ApplicationContext가 된다고 함
@Transactional // @Test로 붙은 것들을 한번 실행 시킨 후 rollback을 한다.
public class BoardControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private BoardServiceImpl boardService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    public void uploadBoard() throws Exception {
        // 1. User를 만든다.
//        User user = new User();
//        user.setEmail("test@nablecomm.com");
//        user.setName("test");
//        user.setPassword("test12");

        // 2. Board를 만든다.
        BoardDto.CreateBoard vo = getCreateBoard();

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

    @Test
    public void deleteBoard() throws Exception {
        Long boardIdx = addCommentService();

        ResultActions result = mockMvc.perform(delete("/api/v1/board/delete/"+boardIdx));

        result.andDo(print());
        result.andExpect(status().isOk());
    }

    //    @Test

    @Test
    public void findAllBoardPage() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/board/all?size=3&page=1"));

        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void findByIdBoard() throws Exception {
        // 2. Board를 만든다.
        BoardDto.CreateBoard vo = getCreateBoard();

        ResultActions result = mockMvc.perform(post("/api/v1/board/upload/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vo)));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.title", is("test")));

        String content = result.andReturn().getResponse().getContentAsString();

        int boardIdx = JsonPath.read(content, "$.idx");

        ResultActions result2 = mockMvc.perform(get("/api/v1/board/" + boardIdx));

        result2.andDo(print());
        result2.andExpect(status().isOk());
    }

    @Test
    public void addComment() throws Exception {
        // 1. Board를 만든다.
        addCommentService();
    }

//    @Test
//    public void commentFindById() throws Exception {
//    }

    private Long addCommentService() throws Exception {
        BoardDto.CreateBoard vo = getCreateBoard();

        ResultActions result = mockMvc.perform(post("/api/v1/board/upload/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vo)));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.title", is("test")));

        String content = result.andReturn().getResponse().getContentAsString();

        int boardIdx = JsonPath.read(content, "$.idx");
        String email = JsonPath.read(content, "$.email");

        // 만들어진 Board의 idx를 알아내고, url에 붙인다.
        Board board = boardService.getBoard(Long.valueOf(boardIdx));

        CommentDto.CreateComment createComment = new CommentDto.CreateComment();
        Comment comment = new Comment(createComment);
        comment.setUploadDate(new Date());
        comment.setBoard(board);
        comment.setEmail(email);
        comment.setContent("Good Job");

        ResultActions result1 = mockMvc.perform(post("/api/v1/board/comment/"+boardIdx)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comment)));

        result1.andDo(print());
        result1.andExpect(status().isOk());

        return Long.valueOf(boardIdx);
    }

    private BoardDto.CreateBoard getCreateBoard() {
        BoardDto.CreateBoard vo = new BoardDto.CreateBoard();
        vo.setTitle("test");
        vo.setEmail("test@nablecomm.com");
        vo.setContent("this is test content");
        vo.setUserIdx((long) 1);
        vo.setTags("Java, Spring");
        return vo;
    }
}
