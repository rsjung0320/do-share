package com.nextinno.doshare.login;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by rsjung on 2016-11-25.
 */
@Data
public class Login {
    private String email;
    private String password;
    private boolean remember;

    public Login(){}

    public Login(LoginDto.SignIn reqLogin){
        this.email = reqLogin.getEmail();
        this.password = reqLogin.getPassword();
        this.remember = reqLogin.isRemember();
    }
}
