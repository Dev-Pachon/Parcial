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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FailingUnitTestsService {

    private static final UUID DOCUMENT_UUID = UUID.fromString("5b666754-e217-4775-9c95-352ebb0673cb");

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    @BeforeEach
    private void init() {
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.
    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentThatNotExists() {
        try {
            documentService.updateDocument(createDummyDocument("notExists", DocumentStatus.REVIEW));
            fail();
        } catch (DocumentException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("Cannot update a document that not exists", error.getMessage());
            assertEquals("CODE_04", error.getCode().name());
        }
    }

    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved() {
        Document document = createDummyDocument("approved", DocumentStatus.APPROVED);
        when(documentRepository.findById(any())).thenReturn(Optional.ofNullable(document));
        try {
            documentService.updateDocument(document);
            fail();
        } catch (DocumentException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("Cannot update a document that have status approved", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());
        }
    }

    private Document createDummyDocument(String name, DocumentStatus documentStatus) {
        return Document.builder().name(name).documentId(DOCUMENT_UUID).status(documentStatus).text("random").priority(1).build();
    }
}
