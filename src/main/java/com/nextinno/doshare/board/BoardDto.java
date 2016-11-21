/*
 * Copyright (C) 2016, Nable Communications, Inc.
 * All rights reserved.
 * 
 * This software is confidential and proprietary information
 * of Nable Communications, Inc.
 */
package com.nextinno.doshare.board;

import java.util.List;

import com.nextinno.doshare.comment.Comment;
import com.nextinno.doshare.tags.Tag;

import lombok.Data;

/**
 * @author rsjung
 *
 */

public class BoardDto {
    
    @Data
    public static class ResponseBoardList{
        private long idx = 0;
        private String title = "";
        private String email = "";
        private String uploadDate = "";
        private String updatedDate = "";
        private int readCount = 0;
    }
    
    @Data
    public static class ResponseBoard{
        private long idx = 0;
        private String title = "";
        private String email = "";
        private String uploadDate = "";
        private String updatedDate = ""; 
        private int readCount = 0;
        private String content = "";
        private List<Comment> comments = null;
        private List<Tag> tags = null;
    }
}
