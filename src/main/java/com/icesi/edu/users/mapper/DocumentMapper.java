package com.icesi.edu.users.mapper;

import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.model.Document;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    Document fromDTO(DocumentDTO documentDTO);

    DocumentDTO fromDocument(Document document);

    Document fromDTO(UUID documentId, DocumentDTO documentDTO);
}
