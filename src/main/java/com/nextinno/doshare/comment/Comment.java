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
    private long idx = 0;
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
    @JsonIgnore
    private Board board;
}
