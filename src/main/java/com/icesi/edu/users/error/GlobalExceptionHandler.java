package com.icesi.edu.users.error;

import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DocumentError> handleException(DocumentException documentException){
        return new ResponseEntity<>(documentException.getError(), documentException.getHttpStatus());
    }

}
