package com.icesi.edu.users.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentStatus {

    CREATED("created"),
    REVIEW("review"),
    APPROVED("approved");


    private final String status;


}
