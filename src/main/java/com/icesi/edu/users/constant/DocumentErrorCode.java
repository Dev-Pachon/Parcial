package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentErrorCode {

    CODE_01("fix this"),
    CODE_02("Only letters and spaces are allowed in name");

    private String message;
}
