package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentErrorCode {

    CODE_01("fix this"),CODE_02("fix this aswell"),CODE_03("Something went wrong");
    private String message;
}
