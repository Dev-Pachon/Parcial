package com.icesi.edu.users.error.exception;

import com.icesi.edu.users.constant.DocumentErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentError extends Throwable {
    private DocumentErrorCode code;
    private String message;
}
