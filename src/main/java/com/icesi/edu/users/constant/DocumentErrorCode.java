package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentErrorCode {

    CODE_03("The document has been already approved"),
    CODE_02("Only letters and spaces are allowed in name"),
    CODE_01("Document not found");

    private String message;
}
