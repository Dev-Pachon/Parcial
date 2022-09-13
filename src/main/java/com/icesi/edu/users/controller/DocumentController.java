package com.icesi.edu.users.controller;

import com.icesi.edu.users.api.DocumentAPI;
import com.icesi.edu.users.constant.DocumentErrorCode;
import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import com.icesi.edu.users.mapper.DocumentMapper;
import com.icesi.edu.users.service.DocumentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class DocumentController implements DocumentAPI {

    private final DocumentMapper documentMapper;

    private final DocumentService documentService;
    @Override
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        if(nameOnlyContainsLettersAndSpaces(documentDTO.getName()))
            return documentMapper.fromDocument(documentService.createDocument(documentMapper.fromDTO(documentDTO)));
        else
            throw new DocumentException(HttpStatus.BAD_REQUEST, new DocumentError(DocumentErrorCode.CODE_02,"Only letters and spaces are allowed in name"));

    }

    @Override
    public DocumentDTO getDocument(UUID documentId) {
        return documentMapper.fromDocument(documentService.getDocument(documentId));
    }

    @Override
    public List<DocumentDTO> getDocuments() {
        return documentService.getDocuments().stream().map(documentMapper::fromDocument).collect(Collectors.toList());
    }

    @Override
    public DocumentDTO updateDocument(UUID documentId, DocumentDTO documentDTO) {
        return documentMapper.fromDocument(documentService.updateDocument(documentMapper.fromDTO(documentId,documentDTO)));
    }

    public boolean nameOnlyContainsLettersAndSpaces (String name) {
        return name.matches("^[a-zA-Z]\\s?");
    }

}
