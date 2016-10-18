package com.nextinno.doshare.domain.comments;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextinno.doshare.domain.boards.Board;

/**
 * @author rsjung
 *
 */
@Entity
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
    
//    private long board_idx = 0;
    
    /**
     * @return the idx
     */
    public long getIdx() {
        return idx;
    }
    /**
     * @param idx the idx to set
     */
    public void setIdx(long idx) {
        this.idx = idx;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the uploadDate
     */
    public String getUploadDate() {
        return uploadDate;
    }
    /**
     * @param uploadDate the uploadDate to set
     */
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
    /**
     * @return the goodCount
     */
    public int getGoodCount() {
        return goodCount;
    }
    /**
     * @param goodCount the goodCount to set
     */
    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }
    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }
    
//    /**
//     * @return the board_idx
//     */
//    public long getBoard_idx() {
//        return board_idx;
//    }
//    /**
//     * @param board_idx the board_idx to set
//     */
//    public void setBoard_idx(long board_idx) {
//        this.board_idx = board_idx;
//    }
//    /* (non-Javadoc)
//     * @see java.lang.Object#toString()
//     */
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Comment [idx=").append(idx).append(", email=").append(email).append(", uploadDate=")
//                .append(uploadDate).append(", goodCount=").append(goodCount).append(", content=").append(content)
//                .append(", board=").append(board).append(", board_idx=").append(board_idx).append("]");
//        return builder.toString();
//    }
}
