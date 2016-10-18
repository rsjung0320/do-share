package com.nextinno.doshare.domain.boards;

/**
 * @author rsjung
 *
 */
public class BoardVo {
    private long idx = 0;
    private String title = "";
    private String email = "";
    private String uploadDate = "";
    private String updatedDate = "";
    private int readCount = 0;
    private String imagePath = "";
    private String content = "";
    private long userIdx = 0;
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
    /**
     * @return the userIdx
     */
    public long getUserIdx() {
        return userIdx;
    }
    /**
     * @param userIdx the userIdx to set
     */
    public void setUserIdx(long userIdx) {
        this.userIdx = userIdx;
    }
}
