package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentErrorCode {

    CODE_01("Document not found"),
    CODE_02("Only letters and spaces are allowed in name"),
    CODE_03("Can't update a document with approved status");

    private String message;
}
