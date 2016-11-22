package com.nextinno.doshare.global.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by rsjung on 2016-11-22.
 */
@Data
public class ErrorResponse {
    private String message;

    private String code;

    private List<FieldError> errors;

    public static class FieldError {
        private String message;
        private String reasonCode;
    }
}
