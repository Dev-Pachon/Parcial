package com.icesi.edu.users.failingUnitTests;

import com.icesi.edu.users.constant.DocumentStatus;
import com.icesi.edu.users.controller.DocumentController;
import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import com.icesi.edu.users.mapper.DocumentMapper;
import com.icesi.edu.users.mapper.DocumentMapperImpl;
import com.icesi.edu.users.model.Document;
import com.icesi.edu.users.repository.DocumentRepository;
import com.icesi.edu.users.service.DocumentService;
import com.icesi.edu.users.service.impl.DocumentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FailingUnitTestsService {

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    private DocumentController documentController;
    private DocumentMapper documentMapper;


    @BeforeEach
    private void init(){
        documentMapper = new DocumentMapperImpl();

        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
        documentController = new DocumentController(documentMapper, documentService);

    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.

    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved(){
        UUID idRandom= UUID.randomUUID();
        Document document= new Document(idRandom,"random","random",1,DocumentStatus.APPROVED);


        when(documentRepository.findById(any())).thenReturn(Optional.empty() );

        try {
            documentService.updateDocument(document);
                    fail();
        }catch (DocumentException e){
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("Document can't update", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());
        }

    }


}
