package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DocumentStatus {

    CREATED("CREATED"),
    REVIEW("REVIEW"),
    APPROVED("APPROVED");


    private String status;


}
