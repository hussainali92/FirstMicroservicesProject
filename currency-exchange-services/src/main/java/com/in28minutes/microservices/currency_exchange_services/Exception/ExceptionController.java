package com.in28minutes.microservices.currency_exchange_services.Exception;

import com.in28minutes.microservices.currency_exchange_services.NotFound;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFound.class)
    public final @Nullable ResponseEntity<Object> handle1Exception(Exception ex, WebRequest request) throws Exception{
        ErrorDetails errorDetails =new ErrorDetails(ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
