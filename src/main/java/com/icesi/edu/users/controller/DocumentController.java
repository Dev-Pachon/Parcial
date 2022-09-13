package com.icesi.edu.users.controller;

import com.icesi.edu.users.api.DocumentAPI;
import com.icesi.edu.users.constant.DocumentErrorCode;
import com.icesi.edu.users.constant.DocumentStatus;
import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import com.icesi.edu.users.mapper.DocumentMapper;
import com.icesi.edu.users.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class DocumentController implements DocumentAPI {

    private final DocumentMapper documentMapper;

    private final DocumentService documentService;

    @Override
    @RequestMapping(value= {"/new"}, method = RequestMethod.POST)
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        hasSpecialCharacters(documentDTO.getName());
        return documentMapper.fromDocument(documentService.createDocument(documentMapper.fromDTO(documentDTO)));
    }

    private void hasSpecialCharacters(String stringToValidate){
        Pattern pattern = Pattern.compile("[^a-zA-Z ]");
        Matcher matcher = pattern.matcher(stringToValidate);

        if(matcher.find()){
            throw new DocumentException(HttpStatus.BAD_REQUEST, new DocumentError(DocumentErrorCode.CODE_02, DocumentErrorCode.CODE_02.getMessage()));
        }
    }

    @Override
    @RequestMapping(value= {"/new"}, method = RequestMethod.GET)
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
