package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentErrorCode {

    CODE_01("CODE_01"),

    CODE_02("CODE_02") ,
    CODE_03("CODE_03") ;

    private String message;
}
