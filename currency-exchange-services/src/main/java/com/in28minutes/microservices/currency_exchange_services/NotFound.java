package com.in28minutes.microservices.currency_exchange_services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {
    public NotFound(String message){
        super(message);
    }
}
