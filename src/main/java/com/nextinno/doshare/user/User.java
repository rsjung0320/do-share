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
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long idx;

    @Column(name = "email", nullable = false, unique = true)
    private String email = "";

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "password", nullable = false)
    private String password = "";

    // user / admin
    @Column(name = "role", nullable = false)
    private String role = "user";

    public User() {}

    public User(UserDto.CreateUser user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();

        if (!user.getRole().equals("admin")) {
            this.role = "user";
        }
    }
}
