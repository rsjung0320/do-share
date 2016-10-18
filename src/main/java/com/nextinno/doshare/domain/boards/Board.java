package com.nextinno.doshare.domain.boards;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.nextinno.doshare.domain.comments.Comment;
import com.nextinno.doshare.domain.users.User;

/**
 * @author rsjung
 *
 */
@Entity
@DynamicUpdate
public class Board {
    // index
    @Id
    @GeneratedValue
    private long idx = 0;
    // 글 제목
    @Column(name = "title", nullable = false)
    private String title = "";
    // 글쓴이
    @Column(name = "email", nullable = false)
    private String email = "";
    // 글 쓴 날짜
    @Column(name = "upload_date", nullable = false)
    private String uploadDate = "";
    // 최종 수정
    @Column(name = "updated_date")
    private String updatedDate = "";
    // 조회 수
    @Column(name = "read_count")
    private int readCount = 0;
    // 이미지 path
    @Column(name = "image_path")
    private String imagePath = "";
    // 글 태그
    @Column(name = "content", nullable = false, columnDefinition = "mediumtext")
    private String content = "";
    // user의 idx
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;
    
    @OneToMany(targetEntity=Comment.class, fetch=FetchType.LAZY)
    @JoinColumn(name="board_idx", nullable = true)
    private List<Comment> comments;
    
    public Board(){
        
    }
    public Board(BoardVo boardVo){
        super();
        this.title = boardVo.getTitle();
        this.email = boardVo.getEmail();
        this.uploadDate = boardVo.getUploadDate();
        this.updatedDate = boardVo.getUpdatedDate();
        this.readCount = boardVo.getReadCount();
        this.imagePath = boardVo.getImagePath();
        this.content = boardVo.getContent();
        this.user = new User();
        user.setIdx(boardVo.getUserIdx());
    }

    /**
     * @param updatedBoard
     */
    public void update(BoardVo updatedBoard) {
        this.title = updatedBoard.getTitle();
        this.updatedDate = updatedBoard.getUpdatedDate();
        this.content = updatedBoard.getContent();
    }
    
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the updatedDate
     */
    public String getUpdatedDate() {
        return updatedDate;
    }
    /**
     * @param updatedDate the updatedDate to set
     */
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
    /**
     * @return the readCount
     */
    public int getReadCount() {
        return readCount;
    }
    /**
     * @param readCount the readCount to set
     */
    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }
    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
//    /**
//     * @return the user
//     */
//    public User getUser() {
//        return user;
//    }
//    /**
//     * @param user the user to set
//     */
//    public void setUser(User user) {
//        this.user = user;
//    }
//    /* (non-Javadoc)
//     * @see java.lang.Object#toString()
//     */
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Board [idx=").append(idx).append(", title=").append(title).append(", email=").append(email)
//                .append(", uploadDate=").append(uploadDate).append(", updatedDate=").append(updatedDate)
//                .append(", readCount=").append(readCount).append(", imagePath=").append(imagePath).append(", content=")
//                .append(content).append(", user=").append(user).append("]");
//        return builder.toString();
//    }
}

