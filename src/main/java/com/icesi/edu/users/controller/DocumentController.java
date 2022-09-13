package com.icesi.edu.users.controller;

import com.icesi.edu.users.api.DocumentAPI;
import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.mapper.DocumentMapper;
import com.icesi.edu.users.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class DocumentController implements DocumentAPI {

    private final DocumentMapper documentMapper;

    private final DocumentService documentService;

    @Override
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        return documentMapper.fromDocument(documentService.createDocument(documentMapper.fromDTO(documentDTO)));
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
