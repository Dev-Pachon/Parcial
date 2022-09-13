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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class FailingUnitTestsService {
    private DocumentController documentController;
    private DocumentMapper documentMapper;

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    private static final UUID DOCUMENT_UUID = UUID.fromString("5b666754-e217-4775-9c95-352ebb0673cb");



    @BeforeEach
    public void init(){
        documentMapper = new DocumentMapperImpl();
        documentService = mock(DocumentService.class);
        documentController = new DocumentController(documentMapper, documentService);
    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.

    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved(){
        try {
            documentService.updateDocument(createDummyDocument(DOCUMENT_UUID.toString(), DocumentStatus.APPROVED));
            fail();
        } catch (DocumentException e) {
            assertEquals(HttpStatus.FORBIDDEN, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("An approved document can't be updated", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());
        }
    }

    private Document createDummyDocument(String name, DocumentStatus documentStatus) {
        return Document.builder().name(name).documentId(DOCUMENT_UUID).status(documentStatus).text("random").priority(1).build();
    }


}
