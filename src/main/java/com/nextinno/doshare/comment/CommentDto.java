package com.nextinno.doshare.comment;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by rsjung on 2016-11-24.
 */
public class CommentDto {
    @Data
    public static class CreateComment{
        @NotBlank
        private String email = "";
        @NotBlank
        private String content = "";
    }

    @Data
    public static class ResponseComment{
        private Long idx;
        private String email = "";
        private Date uploadDate;
        private int goodCount = 0;
        private String content = "";
    }
}
