package com.icesi.edu.users.service.impl;


import com.icesi.edu.users.constant.DocumentErrorCode;
import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import com.icesi.edu.users.model.Document;
import com.icesi.edu.users.repository.DocumentRepository;
import com.icesi.edu.users.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public Document createDocument(Document documentDTO) {
        return documentRepository.save(documentDTO);
    }

    @Override
    public Document getDocument(UUID documentId) {
        return documentRepository.findById(documentId).orElse(null);
    }

    @Override
    public List<Document> getDocuments() {
        return StreamSupport.stream(documentRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Document updateDocument(Document document) {
        return documentRepository.save(document);
    }


}
