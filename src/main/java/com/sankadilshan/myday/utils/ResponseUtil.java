package com.sankadilshan.myday.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sankadilshan.myday.exception.JsonAccessAndProcessingException;
import com.sankadilshan.myday.model.dto.GeneralResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseUtil {

    private final static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    public static GeneralResponse getGeneralResponse(Object object){
       return GeneralResponse
               .builder()
               .data(object)
               .status(HttpStatus.OK.value())
               .timeStamp(LocalDateTime.now())
               .build();
    }

    private String convertToJson(GeneralResponse response) {
        try {
            ObjectWriter objectWriter =  new ObjectMapper().writer().withDefaultPrettyPrinter();
            return objectWriter.writeValueAsString(response);
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new JsonAccessAndProcessingException(GeneralResponse.class.getName());
        }

    }
}
