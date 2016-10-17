package com.nextinno.doshare.domain.boards;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author rsjung
 *
 */
@SuppressWarnings("serial")
@Entity
public class Board implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(name = "name", nullable = false)
    private String name;
 
    @Column(name = "age", nullable = false)
    private Integer age;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Integer getAge() {
        return age;
    }
 
    public void setAge(Integer age) {
        this.age = age;
    }
//    // index
//    private int idx = 0;
//    // 글 제목
//    private String title = "";
//    // 글쓴이
//    private String email = "";
//    // 글 쓴 날짜
//    private String uploadDate = "";
//    // 최종 수정
//    private String updatedDate = "";
//    // 조회 수
//    private int readCount = 0;
//    // 이미지 path
//    private String imagePath = "";
//    // 글 태그
//    private String content = "";
//    // user의 idx
//    private int authorId = 0;
//   
//    
//    /**
//     * @return the idx
//     */
//    public int getIdx() {
//        return idx;
//    }
//    /**
//     * @param idx the idx to set
//     */
//    public void setIdx(int idx) {
//        this.idx = idx;
//    }
//    /**
//     * @return the title
//     */
//    public String getTitle() {
//        return title;
//    }
//    /**
//     * @param title the title to set
//     */
//    public void setTitle(String title) {
//        this.title = title;
//    }
//    /**
//     * @return the email
//     */
//    public String getEmail() {
//        return email;
//    }
//    /**
//     * @param email the email to set
//     */
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    /**
//     * @return the uploadDate
//     */
//    public String getUploadDate() {
//        return uploadDate;
//    }
//    /**
//     * @param uploadDate the uploadDate to set
//     */
//    public void setUploadDate(String uploadDate) {
//        this.uploadDate = uploadDate;
//    }
//    /**
//     * @return the updatedDate
//     */
//    public String getUpdatedDate() {
//        return updatedDate;
//    }
//    /**
//     * @param updatedDate the updatedDate to set
//     */
//    public void setUpdatedDate(String updatedDate) {
//        this.updatedDate = updatedDate;
//    }
//    /**
//     * @return the readCount
//     */
//    public int getReadCount() {
//        return readCount;
//    }
//    /**
//     * @param readCount the readCount to set
//     */
//    public void setReadCount(int readCount) {
//        this.readCount = readCount;
//    }
//    /**
//     * @return the imagePath
//     */
//    public String getImagePath() {
//        return imagePath;
//    }
//    /**
//     * @param imagePath the imagePath to set
//     */
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }
//    /**
//     * @return the content
//     */
//    public String getContent() {
//        return content;
//    }
//    /**
//     * @param content the content to set
//     */
//    public void setContent(String content) {
//        this.content = content;
//    }
//    /**
//     * @return the authorId
//     */
//    public int getAuthorId() {
//        return authorId;
//    }
//    /**
//     * @param authorId the authorId to set
//     */
//    public void setAuthorId(int authorId) {
//        this.authorId = authorId;
//    }
//    
//    /* (non-Javadoc)
//     * @see java.lang.Object#toString()
//     */
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Board [idx=").append(idx).append(", title=").append(title).append(", email=").append(email)
//                .append(", uploadDate=").append(uploadDate).append(", updatedDate=").append(updatedDate)
//                .append(", readCount=").append(readCount).append(", imagePath=").append(imagePath).append(", content=")
//                .append(content).append(", authorId=").append(authorId).append("]");
//        return builder.toString();
//    }
}

