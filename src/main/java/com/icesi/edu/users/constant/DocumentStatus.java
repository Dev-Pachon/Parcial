package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DocumentStatus {

    CREATED("created"),
    REVIEW("review"),
    APPROVED("approved");


    private String status;


}
