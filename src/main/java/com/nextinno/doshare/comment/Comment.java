package com.nextinno.doshare.comment;

import javax.persistence.*;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextinno.doshare.board.Board;

import java.util.Date;

/**
 * @author rsjung
 *
 */
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long idx;
    @Column(name = "email", nullable = false)
    private String email = "";
    @Column(name = "upload_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @Column(name = "good_count")
    private int goodCount = 0;
    @Column(name = "content", nullable = false, columnDefinition = "mediumtext")
    private String content = "";
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name="fk_board_comment"), name="board_idx", nullable = false)
    private Board board;

    public Comment(){};
    public Comment(CommentDto.CreateComment comment){
        this.email = comment.getEmail();
        this.uploadDate = new Date();
        this.content = comment.getContent();
    }
}
