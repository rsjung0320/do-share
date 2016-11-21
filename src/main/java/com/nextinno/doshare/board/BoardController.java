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
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
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
// import com.nextinno.doshare.board.mapper.BoardMapper;
import com.nextinno.doshare.comment.Comment;
import com.nextinno.doshare.comment.CommentRepository;
import com.nextinno.doshare.global.domain.GlobalDomain;
import com.nextinno.doshare.tags.Tag;
import com.nextinno.doshare.tags.TagRepository;

/**
 * @author rsjung
 *
 */

@Controller
// RestController를 사용하면 return으로 json로 해준다고 한다. 잭슨을 쓰지 않아도 됨
// @RestController
@RequestMapping(API.BOARD)
@Transactional
@Slf4j
public class BoardController {

    @Value("${file.uploadPath}")
    private String uploadPath;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "upload/image", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GlobalDomain> uploadImage(@RequestParam(value = "file") MultipartFile file) {
        GlobalDomain globalDomain = new GlobalDomain();
        String filePath = "";
        log.info("name : " + file.getOriginalFilename());
        UUID uuid = UUID.randomUUID();
        try {
            String splitData[] = file.getOriginalFilename().split("\\.");
            String fileType = splitData[splitData.length - 1];

            filePath = uploadPath + uuid.toString() + "." + fileType;

            saveFile(file.getInputStream(), filePath);

            globalDomain.setMessage(uuid.toString() + "." + fileType);
        } catch (IOException e) {
            log.error("uploadImage : ", e);
            globalDomain.setMessage("uploadImage : " + e);
            return new ResponseEntity<GlobalDomain>(globalDomain, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<GlobalDomain>(globalDomain, HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "upload/board", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadBoard(@RequestBody final BoardVo boardVo) {
        Board board = new Board(boardVo);

        String tags = boardVo.getTags();

        if (!tags.equals("")) {
            // 태그들을 먼저 split한다.
            String[] tagArray = tags.split(",");

            for (String tagName : tagArray) {
                // 해당하는 태그가 있다면 해당 태그의 count를 1 증가시키고, board와 맵핑한다.
                String toUpperCaseTagName = tagName.trim().toUpperCase();
                if (!toUpperCaseTagName.equals("")) {
                    Tag getTag = tagRepository.findByName(toUpperCaseTagName);

                    if (getTag != null) {
                        getTag.setTaggedCount(getTag.getTaggedCount() + 1);
                        board.getTags().add(getTag);
                    } else {
                        // 없으면 해당 태그를 만든다.
                        Tag newTag = new Tag();
                        newTag.setName(toUpperCaseTagName);
                        newTag.setTaggedCount(newTag.getTaggedCount() + 1);
                        tagRepository.save(newTag);
                        board.getTags().add(newTag);
                    }
                }
            }
        }

        boardRepository.save(board);
        return new ResponseEntity(HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "upload/edited/board/{idx}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadEditedBoard(@PathVariable long idx, @RequestBody final BoardVo updatedBoard) {
        Board board = boardRepository.findOne(idx);

        // 기존의 board를 가져와서 지우고!
        // tag는 -1씩 카운트를 내린다.
        // 만약 0이 될 경우 tag 테이블에서 지우면 된다.!
        // 현재 값으로 저장하면 된다.
        // 아래 작업은 필요 없다.

        String savedTags = "";
        // 아무 것도 없을 시 split ',' 을 해도 1의 값이 나오기 때문에 초기화 값은 1로 한다.
        int savedTagsSize = 1;
        // 1. 먼저 board의 tags의 값을 가져온다.

        List<Tag> savedTagsList = board.getTags();
        if (savedTagsList.size() != 0) {
            for (Tag tag : savedTagsList) {
                savedTags += tag.getName() + ",";
            }
            savedTags = savedTags.substring(0, savedTags.length() - 1);
        }
        // 2. client로 부터 들어온 값을 확인한다.
        String newTags = updatedBoard.getTags().trim();

        // 3. 기존의 tags 값과 신규 tags 값을 확인한다.
        String[] newTagsArray = newTags.split(",");

        // 분류 작업을 하면 될 듯 한대?

        if (newTagsArray.length == savedTagsSize) {
            // 1. 완전히 같은 경우, 이 부분은 그냥 넘긴다.

            // 2. 다른게 있을 경우
            // 2-1. 기존의 존재하는 것에서 tag 카운트를 빼면서 board_tag에서 삭제한다.
            // 2-2. 새로운 tag는 삽입하고, board_tag에 넣는다.

        } else {
            // 1. 다른게 있을 경우
            // 1-1. 기존의 존재하는 것에서 tag 카운트를 빼면서 board_tag에서 삭제한다.
            // 1-2. 새로운 tag는 삽입하고, board_tag에 넣는다.
        }

        board.update(updatedBoard);

        boardRepository.save(board);

        return new ResponseEntity(HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "delete/{idx}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity deleteBoard(@PathVariable String idx) {
        // boardMapper.deleteBoard(idx);
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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity findAllBoard() {
        List<Board> resultBoard = boardRepository.findAll();

        List<BoardDto.boardList> boardList =
                resultBoard.stream().map(board -> modelMapper.map(board, BoardDto.boardList.class))
                        .collect(Collectors.toList());
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    // @RequestMapping(value = "all", method = RequestMethod.GET)
    // @ResponseBody
    // public ResponseEntity<Page<Board>> findAllBoard(@PageableDefault(direction = Direction.DESC,
    // size = 2) Pageable pageable) {
    // Page<Board> resultBoard = boardRepository.findAll(pageable);
    //
    // return new ResponseEntity<Page<Board>>(resultBoard, HttpStatus.OK);
    // }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "{idx}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity findByIdBoard(@PathVariable long idx) {
        // Board resultBoard = boardMapper.findById(idx);
        Board board = boardRepository.findOne(idx);

        // resultBoard.getTags();
        board.setReadCount(board.getReadCount() + 1);
        // readCount를 1증가 시킨다.
        // boardMapper.updateReadCount(resultBoard);
        Board resultBoard = boardRepository.save(board);
        
        // to-do select 해온 값으로 보내록 한다. 지금은 set한 값으로 주고 있다.
        return new ResponseEntity<>(modelMapper.map(resultBoard, BoardDto.board.class), HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "comment/{idx}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addComment(@RequestBody final Comment comment, @PathVariable long idx) {
        Board board = boardRepository.findOne(idx);
        
        comment.setBoard(board);

        commentRepository.save(comment);
        
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "comment/{idx}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Comment>> commentFindById(@PathVariable long idx) {
        // Board board = new Board();
        // board.setIdx(idx);
        // List<Comment> resultComment = commentRepository.findByBoard(board);
        List<Comment> resultComment = commentRepository.findByBoardIdx(idx);

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
                    log.error("outpuStream.close(). e : ", e);
                }
        }
    }
}
