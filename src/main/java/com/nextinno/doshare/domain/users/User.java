package com.nextinno.doshare.domain.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nextinno.doshare.domain.boards.Board;

/**
 * @author rsjung
 *
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private long idx;
    
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
