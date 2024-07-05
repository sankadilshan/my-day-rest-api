////package com.sankadilshan.myday.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sankadilshan.myday.model.dto.JwtAuthentication;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.util.Date;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            JwtAuthentication authDto = objectMapper.readValue(request.getInputStream(), JwtAuthentication.class);
//            Authentication authentication = new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
//            return authenticationManager.authenticate(authentication);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        log.info(request.toString());
//        log.info(authResult.toString());
//        String token = Jwts.builder()
//                .setSubject(authResult.getName())
//                .claim("authorities", authResult.getAuthorities())
//                .claim("principal", authResult.getPrincipal())
//                .setIssuedAt(new Date())
//                .setIssuer("sanka_dilshan")
//                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.UTC)))
//                .signWith(SignatureAlgorithm.HS256,"5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437")
//                .compact();
//
//        log.info("token {}", token);
//    }
//}
