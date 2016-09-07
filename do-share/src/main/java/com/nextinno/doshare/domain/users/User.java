package com.nextinno.doshare.domain.users;

/**
 * @author rsjung
 *
 */
public class User {
    private String userName = "";
    private String password = "";
    private String name = "";      
    private int isAccountNonExpired = 0;
    private int isAccountNonLocked = 0;
    private int isCredentialsNonExpired = 0;
    private int isEnabled = 0;
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the isAccountNonExpired
     */
    public int getIsAccountNonExpired() {
        return isAccountNonExpired;
    }
    /**
     * @param isAccountNonExpired the isAccountNonExpired to set
     */
    public void setIsAccountNonExpired(int isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }
    /**
     * @return the isAccountNonLocked
     */
    public int getIsAccountNonLocked() {
        return isAccountNonLocked;
    }
    /**
     * @param isAccountNonLocked the isAccountNonLocked to set
     */
    public void setIsAccountNonLocked(int isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }
    /**
     * @return the isCredentialsNonExpired
     */
    public int getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
    /**
     * @param isCredentialsNonExpired the isCredentialsNonExpired to set
     */
    public void setIsCredentialsNonExpired(int isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }
    /**
     * @return the isEnabled
     */
    public int getIsEnabled() {
        return isEnabled;
    }
    /**
     * @param isEnabled the isEnabled to set
     */
    public void setIsEnabled(int isEnabled) {
        this.isEnabled = isEnabled;
    }
      
}
