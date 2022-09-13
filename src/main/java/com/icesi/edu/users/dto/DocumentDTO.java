package com.icesi.edu.users.dto;

import com.icesi.edu.users.constant.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {

    private UUID documentId;
    private String name;
    private String text;
    private int priority;
    private DocumentStatus status;

    public static DocumentDTOBuilder builder() {

        return new DocumentDTOBuilder();
    }

    public static class DocumentDTOBuilder {

        private UUID documentId;
        private String name;
        private String text;
        private int priority;
        private DocumentStatus status;

        public DocumentDTO build(){
            return new DocumentDTO();
        }

        public DocumentDTOBuilder documentId(UUID documentId) {

            this.documentId = documentId;
            return this;
        }

        public DocumentDTOBuilder name(String name) {

            this.name = name;
            return this;
        }

        public DocumentDTOBuilder text(String text) {
            this.text = text;
            return this;
        }


        public DocumentDTOBuilder priority(int priority) {

            this.priority = priority;
            return this;
        }


        public DocumentDTOBuilder status(DocumentStatus status) {

            this.status = status;
            return this;
        }
    }
}
