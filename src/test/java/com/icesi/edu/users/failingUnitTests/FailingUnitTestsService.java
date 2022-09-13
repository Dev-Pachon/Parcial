package com.icesi.edu.users.failingUnitTests;

import com.icesi.edu.users.constant.DocumentStatus;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
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
import static org.mockito.Mockito.*;

public class FailingUnitTestsService {

    private DocumentRepository documentRepository;
    private DocumentService documentService;

    private Document document;

    private UUID DOCUMENT_UUID = UUID.fromString("5b666754-e217-4775-9c95-352ebb0673cb");

    @BeforeEach
    private void init() {
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    private void scenario() {
        document = Document.builder().documentId(DOCUMENT_UUID).name("test test").priority(0).status(DocumentStatus.APPROVED).build();
    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.
    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved() {
        scenario();
        when(documentRepository.findById(DOCUMENT_UUID)).thenReturn(Optional.of(document));
        try {
            documentService.updateDocument(document);
            fail();
        } catch (DocumentException e) {
            verify(documentRepository, times(0)).save(document);
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("Can't update a document with approved status", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());
        }
    }


}
