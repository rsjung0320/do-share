package com.nextinno.doshare.domain.boards;

/**
 * @author rsjung
 *
 */
public class Board {
    // 글 제목
    private String title = "";
    // 글쓴이
    private String email = "";
    // 글 쓴 날짜
    private String uploadDate = "";
    // 최종 수정
    private String updatedDate = "";
    // 조회 수
    private int readCount = 0;
    // 이미지 path
    private String imagePath = "";
    // 글 태그
    private String content = "";
   
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
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Board [title=" + title + ", email=" + email + ", uploadDate=" + uploadDate + ", updatedDate="
                + updatedDate + ", readCount=" + readCount + ", imagePath=" + imagePath + ", content=" + content + "]";
    }
}

