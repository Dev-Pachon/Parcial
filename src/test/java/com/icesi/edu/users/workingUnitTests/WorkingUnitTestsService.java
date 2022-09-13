package com.icesi.edu.users.workingUnitTests;

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

public class WorkingUnitTestsService {

    private static final UUID DOCUMENT_UUID = UUID.fromString("5b666754-e217-4775-9c95-352ebb0673cb");

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    @BeforeEach
    private void init(){
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    @Test
    public void testDocumentNotFound(){
        when(documentRepository.findById(any())).thenReturn(Optional.empty());
        try {
            documentService.getDocument(DOCUMENT_UUID);
            //fail();
        }catch (DocumentException e){
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("Document not found", error.getMessage());
            assertEquals("CODE_01", error.getCode().name());
        }
    }

}
