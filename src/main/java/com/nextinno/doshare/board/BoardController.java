package com.nextinno.doshare.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nextinno.doshare.api.API;
//import com.nextinno.doshare.board.mapper.BoardMapper;
import com.nextinno.doshare.domain.boards.Board;
import com.nextinno.doshare.domain.boards.BoardRepository;
import com.nextinno.doshare.domain.comments.Comment;
import com.nextinno.doshare.domain.comments.CommentRepository;
import com.nextinno.doshare.global.domain.GlobalDomain;

/**
 * @author rsjung
 *
 */
@Controller
@RequestMapping(API.BOARD)
@Transactional(value = "transactionManager")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Value("${file.uploadPath}")
    private String uploadPath;

//    @Autowired
//    private BoardMapper boardMapper;
    
    @Autowired
    private BoardRepository boardRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "upload/image", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GlobalDomain> uploadImage(@RequestParam(value = "file") MultipartFile file) {
        GlobalDomain globalDomain = new GlobalDomain();
        String filePath = "";
        logger.info("name : " + file.getOriginalFilename());
        UUID uuid = UUID.randomUUID();
        try {
            String splitData[] = file.getOriginalFilename().split("\\.");
            String fileType = splitData[splitData.length - 1];
            
            filePath = uploadPath + uuid.toString() + "." + fileType;
            
            saveFile(file.getInputStream(), filePath);

            globalDomain.setMessage(uuid.toString() + "." + fileType);
        } catch (IOException e) {
            logger.error("uploadImage : ", e);
            globalDomain.setMessage("uploadImage : " + e);
            return new ResponseEntity<GlobalDomain>(globalDomain, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<GlobalDomain>(globalDomain, HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "upload/board", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadBoard(@RequestBody final Board board) {

        boardRepository.save(board);
        logger.info("board : " + board.toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "upload/edited/board", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadEditedBoard(@RequestBody final Board board) {
        logger.info("uploadEditedBoard : " + board.toString());
        boardRepository.save(board);
        return new ResponseEntity(HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "delete/{idx}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity deleteBoard(@PathVariable String idx) {
//        boardMapper.deleteBoard(idx);
        boardRepository.delete(Long.valueOf(idx));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "download/{name}", method = RequestMethod.GET)
    @ResponseBody
    public void download(@PathVariable String name, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        File file = new File(uploadPath + name + ".png");
        System.out.println("DownloadView --> file.getPath() : " + file.getPath());
        System.out.println("DownloadView --> file.getName() : " + file.getName());

        response.setContentType("application/download; utf-8");
        response.setContentLength((int) file.length());

        String userAgent = request.getHeader("User-Agent");
        boolean ie = userAgent.indexOf("MSIE") > -1;
        String fileName = null;

        if (ie) {
            fileName = URLEncoder.encode(file.getName(), "utf-8");
        } else {
            fileName = new String(file.getName().getBytes("utf-8"));
        }// end if;

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                }
            }
        }
        out.flush();
    }

    @Transactional
    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Board>> findAllBoard(HttpServletRequest request, HttpServletResponse response) {
        List<Board> resultBoard = boardRepository.findAll();

        return new ResponseEntity<List<Board>>(resultBoard, HttpStatus.OK);
    }

    @RequestMapping(value = "{idx}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Board> findById(@PathVariable long idx, HttpServletRequest request, HttpServletResponse response) {
//        Board resultBoard = boardMapper.findById(idx);
        Board resultBoard = boardRepository.findOne(idx);
        resultBoard.setReadCount(resultBoard.getReadCount() + 1);
        // readCount를 1증가 시킨다.
//        boardMapper.updateReadCount(resultBoard);
        boardRepository.save(resultBoard);
        //to-do select 해온 값으로 보내록 한다. 지금은 set한 값으로 주고 있다.
        return new ResponseEntity<Board>(resultBoard, HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "comment/{idx}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addComment(@RequestBody final Comment comment, @PathVariable long idx) {
        Board board = new Board();
        board.setIdx(idx);
        comment.setBoard(board);
        
        commentRepository.save(comment);
        logger.info("comment : " + comment.toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "comment/{idx}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Comment>> commentFindById(@PathVariable long idx) {
        List<Comment> resultComment = commentRepository.findByBoardIdx(idx);
        logger.info("comment : " + resultComment.toString());
        return new ResponseEntity<List<Comment>>(resultComment, HttpStatus.OK);
    }

    public void saveFile(InputStream uploadedInputStream, String serverLocation) throws IOException {
        OutputStream outpuStream = null;

        try {
            outpuStream = new FileOutputStream(new File(serverLocation));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }

            outpuStream.flush();
            outpuStream.close();

        } catch (IOException e) {
            throw e;
        } finally {
            // 마지막에 FileInputStream과 FileOutputStream을 닫아준다.
            if (outpuStream != null)
                try {
                    outpuStream.close();
                } catch (IOException e) {
                    logger.error("outpuStream.close(). e : ", e);
                }
        }
    }
}
