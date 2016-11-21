package com.nextinno.doshare.board;

import lombok.Data;

/**
 * @author rsjung
 *
 */
@Data
public class BoardVo {
    private long idx = 0;
    private String title = "";
    private String email = "";
    private String uploadDate = "";
    private String updatedDate = "";
    private int readCount = 0;
    private String imagePath = "";
    private String content = "";
    private long userIdx = 0;
    private String tags = "";
}
