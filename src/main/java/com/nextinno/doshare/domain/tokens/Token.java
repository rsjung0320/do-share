package com.nextinno.doshare.domain.tokens;

/**
 * @author rsjung
 *
 */
public class Token {
    private String token = "";
    private String refreshToken = "";
    
    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }
    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }
    /**
     * @param refreshToken the refreshToken to set
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Token [token=").append(token).append(", refreshToken=").append(refreshToken).append("]");
        return builder.toString();
    }
}
