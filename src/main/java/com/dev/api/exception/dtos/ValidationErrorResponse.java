package com.dev.api.exception.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    private String field;
    private String message;

}

