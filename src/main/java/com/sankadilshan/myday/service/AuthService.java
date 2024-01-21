package com.sankadilshan.myday.service;

import com.sankadilshan.myday.model.dto.AuthResponse;
import com.sankadilshan.myday.model.dto.MyDayUserInput;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthService {

    AuthResponse signup(MyDayUserInput userInput);

    String generateToken(Map<String, Object> user);

    AuthResponse sign(Map<String, Object> user);
}
