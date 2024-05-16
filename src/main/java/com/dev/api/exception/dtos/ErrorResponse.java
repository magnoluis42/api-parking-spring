package com.dev.api.exception.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
    private  Integer statusCode;

}
