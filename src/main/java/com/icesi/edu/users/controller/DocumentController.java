package com.icesi.edu.users.controller;

import com.icesi.edu.users.api.DocumentAPI;
import com.icesi.edu.users.constant.DocumentErrorCode;
import com.icesi.edu.users.constant.DocumentStatus;
import com.icesi.edu.users.constant.dto.DocumentDTO;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import com.icesi.edu.users.mapper.DocumentMapper;
import com.icesi.edu.users.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class DocumentController implements DocumentAPI {

    private final DocumentMapper documentMapper;

    private final DocumentService documentService;

    @Override
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        if (validateName(documentDTO.getName())){
            return documentMapper.fromDocument(documentService.createDocument(documentMapper.fromDTO(documentDTO)));
        }else throw new DocumentException(HttpStatus.BAD_REQUEST,new DocumentError(DocumentErrorCode.CODE_02,DocumentErrorCode.CODE_02.getMessage()));
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
        if (validateDocumentStatus(documentDTO.getStatus())){
            return documentMapper.fromDocument(documentService.updateDocument(documentMapper.fromDTO(documentId,documentDTO)));
        }else throw new DocumentException(HttpStatus.BAD_REQUEST,new DocumentError(DocumentErrorCode.CODE_03,DocumentErrorCode.CODE_03.getMessage()));
    }

    public boolean validateName(String name){
        if(name.matches("^[a-zA-Z ]+$")) return true;
        else return false;
    }

    public boolean validateDocumentStatus(DocumentStatus status){
        if (status!=DocumentStatus.APPROVED) return true;
        else return false;
    }

}
