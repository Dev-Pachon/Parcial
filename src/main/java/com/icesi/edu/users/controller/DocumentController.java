package com.icesi.edu.users.controller;

import com.icesi.edu.users.api.DocumentAPI;
import com.icesi.edu.users.constant.DocumentErrorCode;
import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import com.icesi.edu.users.mapper.DocumentMapper;
import com.icesi.edu.users.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class DocumentController implements DocumentAPI {

    private final DocumentMapper documentMapper;

    private final DocumentService documentService;

    private final String DOCUMENT_NAME_REGEX = "^[A-Za-z\\s]*$";

    @Override
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        if(isNotNull(documentDTO) && isNotNull(documentDTO.getName()) && checkDocumentName(documentDTO.getName()))
            return documentMapper.fromDocument(documentService.createDocument(documentMapper.fromDTO(documentDTO)));
        throw new DocumentException(HttpStatus.BAD_REQUEST, new DocumentError(DocumentErrorCode.CODE_02, DocumentErrorCode.CODE_02.getMessage()));
    }

    private boolean isNotNull(Object toCheck) {
        return toCheck != null;
    }

    private boolean checkDocumentName(String name) {
        return name.matches(DOCUMENT_NAME_REGEX);
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

}
