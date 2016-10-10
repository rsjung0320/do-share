package com.nextinno.doshare.domain.users;

/**
 * @author rsjung
 *
 */
public class User {
    private int idx = 0;
    private String email = "";
    private String name = "";   
    private String password = "";
    // user / admin
    private String role = "user";
    
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }
    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [idx=").append(idx).append(", email=").append(email).append(", name=").append(name)
                .append(", password=").append(password).append(", role=").append(role).append("]");
        return builder.toString();
    }
}
