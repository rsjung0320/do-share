package com.nextinno.doshare.domain.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.nextinno.doshare.domain.boards.Board;

/**
 * @author rsjung
 *
 */
@Entity
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
    
    @OneToMany(targetEntity=Board.class)
    @JoinColumn(nullable = true)
    private List<Board> Boards;
}
