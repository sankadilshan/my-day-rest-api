package com.sankadilshan.myday.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class GeneralResponse {

    private Object data;
    private int status;
    private LocalDateTime timeStamp;

}
