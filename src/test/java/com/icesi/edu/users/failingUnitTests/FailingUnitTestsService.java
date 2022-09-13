package com.icesi.edu.users.failingUnitTests;

import com.icesi.edu.users.constant.DocumentStatus;
import com.icesi.edu.users.controller.DocumentController;
import com.icesi.edu.users.constant.dto.DocumentDTO;
import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import com.icesi.edu.users.mapper.DocumentMapper;
import com.icesi.edu.users.mapper.DocumentMapperImpl;
import com.icesi.edu.users.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class FailingUnitTestsService {

    private static final UUID DOCUMENT_UUID = UUID.fromString("5b666754-e217-4775-9c95-352ebb0673cb");

    private DocumentController documentController;
    private DocumentMapper documentMapper;

    private DocumentService documentService;

    @BeforeEach
    public void init() {
        documentMapper = new DocumentMapperImpl();
        documentService = mock(DocumentService.class);
        documentController = new DocumentController(documentMapper, documentService);
    }

    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved(){
        try {
            documentController.updateDocument(DOCUMENT_UUID,createDummyDocument("David", DocumentStatus.APPROVED));
            fail();
        } catch (DocumentException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("Document status is APPROVED", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());
        }
    }

    private DocumentDTO createDummyDocument(String name, DocumentStatus documentStatus) {
        return DocumentDTO.builder().name(name).documentId(DOCUMENT_UUID).status(documentStatus).text("random").priority(1).build();
    }
}
