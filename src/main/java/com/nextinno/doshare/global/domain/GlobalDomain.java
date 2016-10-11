package com.nextinno.doshare.global.domain;

/**
 * @author rsjung
 *
 */
public class GlobalDomain {
    private String message = "";

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GlobalDomain [message=").append(message).append("]");
        return builder.toString();
    }
}
