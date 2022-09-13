package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentStatus {

    CREATED("created"),
    REVIEW("review"),
    APPROVED("approved");


    private String status;


}
