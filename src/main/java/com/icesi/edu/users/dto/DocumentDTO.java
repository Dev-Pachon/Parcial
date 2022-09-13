package com.icesi.edu.users.dto;

import com.icesi.edu.users.constant.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Getter
@AllArgsConstructor
@Builder
public class DocumentDTO {

    private UUID documentId;
    private String name;
    private String text;
    private int priority;
    private DocumentStatus status;


}
