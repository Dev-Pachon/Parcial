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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FailingUnitTestsService {

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    private static final UUID DOCUMENT_UUID = UUID.fromString("5b666754-e217-4775-9c95-352ebb0673cb");

    @BeforeEach
    private void init() {
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.
    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved() {
        Document testDoc = createDummyDocument();
        when(documentRepository.findById(any())).thenReturn(Optional.of(testDoc));
        try {
            testDoc = createDocumentWithUpdatedInfo(testDoc);
            documentService.updateDocument(testDoc);
            fail();
        } catch (DocumentException e) {
            assertEquals(HttpStatus.I_AM_A_TEAPOT, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("Document cannot be updated because it's been already approved", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());
        }
    }

    private Document createDummyDocument() {
        Document doc = new Document(DOCUMENT_UUID, "Old Name", "Old text bbb",1, DocumentStatus.APPROVED);
        return doc;
    }

    private Document createDocumentWithUpdatedInfo(Document doc) {
        doc.setName("New name");
        doc.setPriority(3);
        doc.setText("New text aaaaa");
        return doc;
    }
}
