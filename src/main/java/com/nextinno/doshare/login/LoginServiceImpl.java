package com.nextinno.doshare.login;

import com.nextinno.doshare.common.Common;
import com.nextinno.doshare.token.Token;
import com.nextinno.doshare.token.TokenDto;
import com.nextinno.doshare.user.AlreadyExistUserException;
import com.nextinno.doshare.user.User;
import com.nextinno.doshare.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rsjung on 2016-11-25.
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String SECRET;

    @Override
    public User saveUser(User user) {
        // 1. DB에 값이 있는지 확인한다.
        User resultUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (resultUser != null) {
            // 기존에 존재하는 User가 있으면 에러메시지를 response 한다.
            throw new AlreadyExistUserException(resultUser.getEmail());
        }

        return userRepository.save(user);
    }

    @Override
    public Token getToken(Login login) {
        Token token = new Token();

        // 1. DB에서 값을 가져온다.
        User resultUser = userRepository.findByEmail(login.getEmail());
        if (resultUser != null) {
            // 2. password를 비교한다.
            if (resultUser.getPassword().equals(login.getPassword())) {
                // 3. 아무런 변경이 없으면 token을 발행한다.
                token.setToken(Common.generateToken(resultUser.getEmail(), resultUser.getRole()));
                // remember me를 누른 사람만 준다.
                if(login.isRemember()) {
                    token.setRefreshToken(Common.generateRefreshToken(resultUser.getEmail(), resultUser.getRole()));
                }
                return token;
            } else {
                throw new NotEqualPasswordException(resultUser.getEmail());
            }
        } else {
            throw new UserNotFoundException(login.getEmail());
        }
    }

    @Override
    public Token refreshToken(TokenDto.RefreshToken refreshToken) {
        Token token = new Token();
        if(isExpired(refreshToken.getRefreshToken())){
            token.setToken(Common.generateToken(refreshToken.getEmail(), refreshToken.getRole()));
            token.setRefreshToken(Common.generateRefreshToken(refreshToken.getEmail(), refreshToken.getRole()));
            return token;
        } else {
            throw new InvalidTokenException(refreshToken.getEmail());
        }
    }

    @Override
    public Token regenerateToken(TokenDto.RegenerateToken regenerateToken) {
        Token token = new Token();
        if (isExpired(regenerateToken.getToken())) {
            token.setToken(Common.generateToken(regenerateToken.getEmail(), regenerateToken.getRole()));
            if (regenerateToken.isRemember()) {
                token.setRefreshToken(Common.generateRefreshToken(regenerateToken.getEmail(), regenerateToken.getRole()));
            }
            return token;
        } else {
            throw new InvalidTokenException(regenerateToken.getEmail());
        }
    }

    /**
     * 완료되었는지 확인
     * @param token
     * @return
     */
    public boolean isExpired(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (final SignatureException e) {
            return false;
        }
        return true;
    }
}
