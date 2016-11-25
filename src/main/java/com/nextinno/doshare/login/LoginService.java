package com.nextinno.doshare.login;

import com.nextinno.doshare.token.Token;
import com.nextinno.doshare.token.TokenDto;
import com.nextinno.doshare.user.User;

/**
 * Created by rsjung on 2016-11-25.
 */
public interface LoginService {
    User saveUser(User user);
    Token getToken(Login login);
    Token refreshToken(TokenDto.RefreshToken token);
    Token regenerateToken(TokenDto.RegenerateToken token);
}
