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
    
//    @OneToMany(targetEntity=Board.class)
//    @JoinColumn(nullable = true)
//    private List<Board> Boards;

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

//    /**
//     * @return the boards
//     */
//    public List<Board> getBoards() {
//        return Boards;
//    }
//
//    /**
//     * @param boards the boards to set
//     */
//    public void setBoards(List<Board> boards) {
//        Boards = boards;
//    }
//
//    /* (non-Javadoc)
//     * @see java.lang.Object#toString()
//     */
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("User [idx=").append(idx).append(", email=").append(email).append(", name=").append(name)
//                .append(", password=").append(password).append(", role=").append(role).append(", Boards=")
//                .append(Boards).append("]");
//        return builder.toString();
//    }
}
