package com.icesi.edu.users.failingUnitTests;

import com.icesi.edu.users.constant.DocumentStatus;
import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
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

    private static final UUID D_UUID = UUID.fromString("5b666754-e217-4775-9c95-352ebb0673cb");

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    @BeforeEach
    private void init(){
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.

    private Document createTestDocument() {
        return Document.builder().name("Testing").documentId(D_UUID).status(DocumentStatus.APPROVED).text("whatever").priority(1).build();
    }
    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved(){

        try {
            documentService.updateDocument(createTestDocument());
            fail();
        } catch (DocumentException e) {
            assertEquals(HttpStatus.METHOD_NOT_ALLOWED, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("Approved, not allow to modified", error.getMessage());
            assertEquals("CODE_02", error.getCode().name());
        }
    }




}
