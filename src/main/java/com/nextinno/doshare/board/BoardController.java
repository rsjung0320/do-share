package com.nextinno.doshare.board;

import com.nextinno.doshare.api.API;
import com.nextinno.doshare.comment.Comment;
import com.nextinno.doshare.comment.CommentDto;
import com.nextinno.doshare.comment.CommentRepository;
import com.nextinno.doshare.comment.CommentServiceImpl;
import com.nextinno.doshare.global.domain.ErrorResponse;
import com.nextinno.doshare.global.domain.GlobalDomain;
import com.nextinno.doshare.tags.Tag;
import com.nextinno.doshare.tags.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author rsjung
 */
// @RestController를 사용하면 return으로 json로 해준다고 한다. 잭슨을 쓰지 않아도 됨
@Controller
@Transactional
@Slf4j
@RequestMapping(API.BOARD)
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
    private CommentServiceImpl commentService;

    @Autowired
    private BoardServiceImpl boardService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "upload/image", method = POST)
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

    @RequestMapping(value = "upload/board", method = POST)
    @ResponseBody
    public ResponseEntity uploadBoard(@RequestBody @Valid final BoardDto.CreateBoard boardVo, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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
                        board.getTag().add(getTag);
                    } else {
                        // 없으면 해당 태그를 만든다.
                        Tag newTag = new Tag();
                        newTag.setName(toUpperCaseTagName);
                        newTag.setTaggedCount(newTag.getTaggedCount() + 1);
                        tagRepository.save(newTag);
                        board.getTag().add(newTag);
                    }
                }
            }
        }

        Board resultBoard = boardRepository.save(board);
        return new ResponseEntity<>(resultBoard, HttpStatus.OK);
    }

    @RequestMapping(value = "upload/edited/board/{idx}", method = POST)
    @ResponseBody
    public ResponseEntity uploadEditedBoard(@PathVariable long idx, @RequestBody @Valid final BoardDto.UpdateBoard updatedBoard, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Board board = boardService.getBoard(idx);

        // 기존의 board를 가져와서 지우고!
        // tag는 -1씩 카운트를 내린다.
        // 만약 0이 될 경우 tag 테이블에서 지우면 된다.!
        // 현재 값으로 저장하면 된다.
        // 아래 작업은 필요 없다.

        String savedTags = "";
        // 아무 것도 없을 시 split ',' 을 해도 1의 값이 나오기 때문에 초기화 값은 1로 한다.
        int savedTagsSize = 1;
        // 1. 먼저 board의 tags의 값을 가져온다.

        List<Tag> savedTagsList = board.getTag();
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

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * board를 삭제한다.
     *
     * @param idx
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "delete/{idx}", method = DELETE)
    @ResponseBody
    public ResponseEntity deleteBoard(@PathVariable String idx) {
        boardRepository.delete(Long.valueOf(idx));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "download/{name}", method = GET)
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

    /**
     * page 및 size에 맞게 boardList를 주는 API
     * ex) http://localhost:8080/all?size=2&page=0&sort=id,desc
     *
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity findAllBoardPage(@PageableDefault(sort = "uploadDate", direction = Direction.DESC) Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);

        List<BoardDto.ResponseBoardList> content = page.getContent().parallelStream()
                .map(board -> modelMapper.map(board, BoardDto.ResponseBoardList.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new PageImpl<>(content, pageable, page.getTotalElements()), HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "{idx}", method = GET)
    @ResponseBody
    public ResponseEntity findByIdBoard(@PathVariable long idx) {
        Board board = boardService.getBoard(idx);

        return new ResponseEntity<>(modelMapper.map(board, BoardDto.ResponseBoard.class), HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "comment/{idx}", method = POST)
    @ResponseBody
    public ResponseEntity addComment(@RequestBody @Valid final CommentDto.CreateComment createComment, @PathVariable Long idx, BindingResult result) {
        if (result.hasErrors() && idx == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Comment comment = new Comment(createComment);

        Board board = boardService.getBoard(idx);

        Comment resultComment = commentService.addComment(board, comment);

        return new ResponseEntity<>(modelMapper.map(resultComment, CommentDto.ResponseComment.class), HttpStatus.OK);
    }

    @RequestMapping(value = "comment/{idx}", method = GET)
    @ResponseBody
    public ResponseEntity commentFindById(@PathVariable Long idx) {
        if (idx == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Comment> commentList = commentRepository.findByBoardIdx(idx);

        List<CommentDto.ResponseComment> resultCommentList = commentList.parallelStream()
                .map(comment -> modelMapper.map(comment, CommentDto.ResponseComment.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(resultCommentList, HttpStatus.OK);
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

    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseBody
    public ResponseEntity handleAccountNotFoundException(BoardNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("[" + e.getId() + "]에 해당하는 게시글이 없습니다.");
        errorResponse.setCode("board.not.found.exception");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
