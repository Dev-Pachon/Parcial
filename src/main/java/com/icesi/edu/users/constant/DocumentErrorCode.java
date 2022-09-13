package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentErrorCode {

    CODE_01("fix this"),
    CODE_02("Document Id not Found"),
    Code_03("Document status is Aproved");

    private String message;
}
