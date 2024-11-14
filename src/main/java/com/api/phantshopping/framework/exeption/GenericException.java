package com.api.phantshopping.framework.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GenericException extends RuntimeException {

    private HttpStatus status;
    private String message;
}