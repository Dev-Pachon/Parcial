package com.icesi.edu.users.controller;

import com.icesi.edu.users.api.DocumentAPI;
import static com.icesi.edu.users.constant.DocumentErrorCode.*;
import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import com.icesi.edu.users.mapper.DocumentMapper;
import com.icesi.edu.users.service.DocumentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        if(verifyDocumentNameFormat(documentDTO)){
            return documentMapper.fromDocument(documentService.createDocument(documentMapper.fromDTO(documentDTO)));
        }
        else{
            throw new DocumentException(HttpStatus.BAD_REQUEST,new DocumentError(CODE_02,CODE_02.getMessage()));
        }

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

    private boolean verifyDocumentNameFormat(DocumentDTO documentDTO){
        return documentDTO.getName().replaceAll(" ","").matches("^[a-zA-Z0-9]*$");
    }

}
