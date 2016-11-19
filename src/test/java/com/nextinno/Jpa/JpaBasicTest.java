//package com.nextinno.Jpa;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.nextinno.doshare.domain.boards.Board;
//import com.nextinno.doshare.domain.boards.BoardRepository;
//import com.nextinno.doshare.domain.comments.Comment;
//import com.nextinno.doshare.domain.users.User;
//import com.nextinno.doshare.main.Main;
//import com.nextinno.doshare.tags.Tag;
//
///**
// * @author rsjung
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Main.class)
//public class JpaBasicTest {
//    
//    @Autowired
//    private BoardRepository boardRepository;
//    
//    /**
//     * @throws java.lang.Exception
//     */
//    @Before
//    public void setUp() throws Exception {}
//
//    @Test
//    public void boardRepository가_있는지_확인() {
//        int count = (int) boardRepository.count();
////        assertEquals(boardRepository);
//        System.out.println("count : " + count);
//        assertEquals(10, count);
//    }
//    
//    @Test
//    @Transactional
//    public void board값_확인(){
//        List<Board> boardList = boardRepository.findAll();
//       
//        System.out.println("boardList : " +  boardList.stream().map(board -> board));
////        for(Board board: boardList){
////            List<Tag> tags = board.getTags();
////            
////            System.out.println(tags.getClass());
////        }
//        
//    }
//
//}
//
