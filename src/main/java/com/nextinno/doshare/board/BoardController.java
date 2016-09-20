package com.nextinno.doshare.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nextinno.doshare.api.API;
import com.nextinno.doshare.board.mapper.BoardMapper;
import com.nextinno.doshare.domain.boards.Board;

/**
 * @author rsjung
 *
 */
@Controller
@RequestMapping(API.BOARD)
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Value("${file.uploadPath}")
    private String uploadPath;

    @Autowired
    private BoardMapper boardMapper;
    
    @RequestMapping(value = "upload/image", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(@RequestParam(value = "file") MultipartFile file) {

        String filePath = "";
        logger.info("name : " + file.getOriginalFilename());
        try {
            // to-do 파일 이름은 새로 만들도록 한다.
            saveFile(file.getInputStream(), uploadPath + file.getOriginalFilename());

            // 저장된 path를 DB에 넣는다.

            // DB에 저장된 URL을 클라이언트에게 보낸다.

            filePath = uploadPath + file.getOriginalFilename();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return file.getOriginalFilename();
    }
    
    @RequestMapping(value = "upload/board", method = RequestMethod.POST)
    @ResponseBody
    public String uploadBoard(@RequestBody final Board board) {
        
        boardMapper.addBoard(board);
        logger.info("board : " + board.toString());
        return "success";
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
