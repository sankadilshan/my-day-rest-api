package com.sankadilshan.myday.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankadilshan.myday.model.MyDayUser;
import com.sankadilshan.myday.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MetaData {

    private static UserService userService;
    
    private final static String metaToken = null;

    @Autowired
    private MetaData(UserService userService) {
        MetaData.userService = userService;
    }

    private static String getCurrentLoginUsername(){
        String userName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userName = authentication.getName();
        }
        return userName;
    }

    public static String generateUserMetaDataEncoded() {
        try {
            String currentUsername = getCurrentLoginUsername();
            if (currentUsername != null) {
                MyDayUser myDayUser = userService.fetchUserByUserName(currentUsername);
                log.info("user details {}", myDayUser);
                return releaseMetaToken(myDayUser);
            }
        }catch (NullPointerException | JsonProcessingException e) {
            log.error("error", e);
        }
      return null;
    }

    private static String releaseMetaToken(MyDayUser myDayUser) throws JsonProcessingException {
        Map<String, Object> claims = new HashMap<>();
        Map<String, Object> userData =  new HashMap<>();

        userData.put("email", myDayUser.getEmail());
        userData.put("roles",myDayUser.getRoles());
        userData.put("firstName",myDayUser.getFirstName());
        userData.put("lastName", myDayUser.getLastName());

        claims.put("userData", userData);
        claims.put("timestamp", LocalDate.now().toString());

        ObjectMapper objectMapper = new ObjectMapper();
        String claimsStr = objectMapper.writeValueAsString(claims);
        String encodeMetaData = Base64.getEncoder().withoutPadding().encodeToString(claimsStr.getBytes(StandardCharsets.UTF_8));
        log.info("encoded meta data {}", encodeMetaData);

        return encodeMetaData;
    }
}

