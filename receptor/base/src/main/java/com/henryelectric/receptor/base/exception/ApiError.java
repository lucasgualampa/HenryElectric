package com.henryelectric.receptor.base.exception;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}
