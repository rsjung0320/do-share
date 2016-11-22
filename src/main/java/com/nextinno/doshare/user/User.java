package com.nextinno.doshare.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author rsjung
 *
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long idx;
    
    @Column(name = "email", nullable = false, unique=true)
    private String email = "";
    
    @Column(name = "name", nullable = false)
    private String name = "";   
    
    @Column(name = "password", nullable = false)
    private String password = "";
    
    // user / admin
    @Column(name = "role", nullable = false)
    private String role = "user";
    
//    @OneToMany(targetEntity=Board.class, fetch=FetchType.LAZY)
//    @JoinColumn(name="user_idx", nullable = true)
//    @JsonIgnore
//    private List<Board> boards;

    /**
     * @return the password
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

}
