/*
 * Copyright (C) 2016, Nable Communications, Inc.
 * All rights reserved.
 * 
 * This software is confidential and proprietary information
 * of Nable Communications, Inc.
 */
package com.nextinno.doshare.domain.boards;

import java.util.List;

import com.nextinno.doshare.domain.comments.Comment;
import com.nextinno.doshare.domain.users.User;
import com.nextinno.doshare.tags.Tag;

import lombok.Data;

/**
 * @author rsjung
 *
 */

public class BoardDto {
    
    @Data
    public static class boardList{
        private long idx = 0;
        private String title = "";
        private String email = "";
        private String uploadDate = "";
        private String updatedDate = "";
        private int readCount = 0;
    }
    
    @Data
    public static class board{
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
