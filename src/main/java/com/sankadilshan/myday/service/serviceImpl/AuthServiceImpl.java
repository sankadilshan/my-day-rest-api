package com.sankadilshan.myday.service.serviceImpl;

import com.sankadilshan.myday.Constants;
import com.sankadilshan.myday.dao.MyDayUserDao;
import com.sankadilshan.myday.exception.UserNameNotFoundException;
import com.sankadilshan.myday.model.dto.AuthResponse;
import com.sankadilshan.myday.model.dto.MyDayUserInput;
import com.sankadilshan.myday.security.JwtService;
import com.sankadilshan.myday.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private MyDayUserDao userDao;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public AuthServiceImpl(MyDayUserDao userDao, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userDao= userDao;
        this.authenticationManager= authenticationManager;
        this.jwtService= jwtService;
    }

    @Override
    public AuthResponse signup(MyDayUserInput userInput) {
        log.info("Auth Service :: signup new user :: service level");
        return userDao.signUp(userInput);
    }

    @Override
    public String generateToken(Map<String, Object> user) {
        log.info("Auth Service :: generate token :: service level");
        Object username = user.get("email");
        Object password = user.get("password");
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authenticate.isAuthenticated()) {
                return jwtService.generateToken(username.toString());
            }
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new UserNameNotFoundException(username.toString());
        }
        return null;
    }

    @Override
    public AuthResponse sign(Map<String, Object> user) {
        String token = generateToken(user);
        return AuthResponse.builder()
                        .message(Constants.SIGN_SUCCESS)
                                .token(token)
                                        .status(HttpStatus.OK).build();
    }
}
