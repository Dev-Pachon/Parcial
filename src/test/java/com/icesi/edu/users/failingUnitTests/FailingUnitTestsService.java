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

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    private Document testDocument;

    private static final UUID DOCUMENT_UUID = UUID.fromString("5b666754-e217-4775-9c95-352ebb0673cb");

    @BeforeEach
    private void init(){
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
        testDocument = createDummyDocument("TestDocument", DocumentStatus.APPROVED);
    }
    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved(){
        when(documentRepository.findById(any())).thenReturn(Optional.of(testDocument));
        Document testDocumentUpdated = createDummyDocument("TestDocument", DocumentStatus.APPROVED);
        testDocumentUpdated.setText("RandomUpdated");
        try {
            documentService.updateDocument(testDocumentUpdated);
            fail();
        }catch (DocumentException e){
            assertEquals(HttpStatus.FORBIDDEN, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("The document is already approved, updates are forbidden", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());
        }
    }

    private Document createDummyDocument(String name, DocumentStatus documentStatus) {
        return Document.builder().name(name).documentId(DOCUMENT_UUID).status(documentStatus).text("random").priority(1).build();
    }


}
