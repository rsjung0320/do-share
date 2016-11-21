package com.nextinno.Jpa;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextinno.doshare.board.BoardDto;
import com.nextinno.doshare.board.BoardRepository;
import com.nextinno.doshare.main.Main;

/**
 * @author rsjung
 *
 */
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
@Transactional // @Test로 붙은 것들을 한번 실행 시킨 후 rollback을 한다.
@WebMvcTest()
public class JpaBasicTest {
    
    @Autowired
    WebApplicationContext wac;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    private BoardRepository boardRepository;
    
    @Autowired
    MockMvc mockMvc;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
////                .addFilter(springSecurityFilterChain)
//                .build();
    }

    @Test
    public void boardRepository가_있는지_확인() {
        
        BoardDto.ResponseBoardList boardList= BoardCreate();
        
        System.out.println("boardList : " + boardList);
        int count = (int) boardRepository.count();
//        assertEquals(boardRepository);
        System.out.println("count : " + count);
        assertEquals(10, count);
    }


    
    @Test
    @Transactional
    public void board값_확인() throws Exception{
        
//        ResultActions result = mockMvc.perform(get("/accounts").);
        
//        System.out.println("boardList : " +  boardList.stream().map(board -> board));
//        for(Board board: boardList){
//            List<Tag> tags = board.getTags();
//            
//            System.out.println(tags.getClass());
//        }
        
    }
    
    /**
     * 
     */
    private BoardDto.ResponseBoardList BoardCreate() {
        BoardDto.ResponseBoardList boardList = new BoardDto.ResponseBoardList();
        
        boardList.setEmail("rsjung@nablecomm.com");
        boardList.setReadCount(7);
        boardList.setTitle("테스트화면");
        String date = new Date().toString();
        boardList.setUpdatedDate(date);
        boardList.setUploadDate(date);
        
        return boardList;
    }

}

