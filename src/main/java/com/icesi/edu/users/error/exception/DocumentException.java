package com.icesi.edu.users.error.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class DocumentException extends RuntimeException {
    private HttpStatus httpStatus;
    private DocumentError error;
}
