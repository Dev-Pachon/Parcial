package com.icesi.edu.users.error.exception;

import com.icesi.edu.users.constant.DocumentErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class DocumentError {
    public DocumentErrorCode code;
    public String message;
}
