package com.icesi.edu.users.mapper;

import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.model.Document;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Primary
public class DocumentMapperMock implements DocumentMapper {
    @Override
    public Document fromDTO(DocumentDTO documentDTO) {
        return null;
    }

    @Override
    public DocumentDTO fromDocument(Document document) {
        return null;
    }

    @Override
    public Document fromDTO(UUID documentId, DocumentDTO documentDTO) {
        return null;
    }
}
