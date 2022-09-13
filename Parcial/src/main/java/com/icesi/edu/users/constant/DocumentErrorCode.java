package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentErrorCode {

    CODE_01("Not found"),
    CODE_02("Approved, not allow to modified"),
    CODE_03("Only letters and spaces are allowed");
    private final String message;
}
