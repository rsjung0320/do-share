/*
 * Copyright (C) 2016, Nable Communications, Inc.
 * All rights reserved.
 * 
 * This software is confidential and proprietary information
 * of Nable Communications, Inc.
 */
package com.nextinno.doshare.board;

import java.util.Date;
import java.util.List;

import com.nextinno.doshare.comment.Comment;
import com.nextinno.doshare.tags.Tag;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author rsjung
 *
 */
public class BoardDto {
    @Data
    public static class CreateBoard{
        @NotBlank
        private String title = "";
        @NotBlank
        private String email = "";
        private Date uploadDate;
        private Date updatedDate;
        private int readCount = 0;
        @NotBlank
        private String content = "";
        @NotNull
        private Long userIdx;
        private String tags = "";
    }

    @Data
    public static class UpdateBoard{
        @NotBlank
        private String title = "";
        private Date updatedDate;
        @NotBlank
        private String content = "";
        private String tags = "";
    }

    @Data
    public static class ResponseBoardList{
        private Long idx;
        private String title = "";
        private String email = "";
        private String uploadDate = "";
        private String updatedDate = "";
        private int readCount = 0;
    }
    
    @Data
    public static class ResponseBoard{
        private Long idx;
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
