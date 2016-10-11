package com.nextinno.doshare.domain.comments;

/**
 * @author rsjung
 *
 */
public class Comment {
    private int idx = 0;
    private String email = "";
    private String uploadDate = "";
    private int goodCount = 0;
    private String content = "";
    private int board_id = 0;
    
    /**
     * @return the idx
     */
    public int getIdx() {
        return idx;
    }
    /**
     * @param idx the idx to set
     */
    public void setIdx(int idx) {
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
     * @return the board_id
     */
    public int getBoard_id() {
        return board_id;
    }
    /**
     * @param board_id the board_id to set
     */
    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Comment [idx=").append(idx).append(", email=").append(email).append(", uploadDate=")
                .append(uploadDate).append(", goodCount=").append(goodCount).append(", content=").append(content)
                .append(", board_id=").append(board_id).append("]");
        return builder.toString();
    }
    
}
