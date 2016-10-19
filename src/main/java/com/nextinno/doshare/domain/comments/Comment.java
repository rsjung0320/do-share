package com.nextinno.doshare.domain.comments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextinno.doshare.domain.boards.Board;

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
    private String uploadDate = "";
    @Column(name = "good_count")
    private int goodCount = 0;
    @Column(name = "content", nullable = false, columnDefinition = "mediumtext")
    private String content = "";
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Board board;
}
