package com.icesi.edu.users.mapper;

import com.icesi.edu.users.constant.dto.DocumentDTO;
import com.icesi.edu.users.model.Document;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    Document fromDTO(DocumentDTO documentDTO);

    DocumentDTO fromDocument(Document document);

    Document fromDTO(UUID documentId, DocumentDTO documentDTO);
}
