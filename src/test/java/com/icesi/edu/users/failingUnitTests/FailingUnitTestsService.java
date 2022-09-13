package com.icesi.edu.users.failingUnitTests;

import com.icesi.edu.users.constant.DocumentStatus;
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

import javax.xml.crypto.Data;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FailingUnitTestsService {

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    private DocumentMapper documentMapper;

    private static final UUID DOCUMENT_UUID = UUID.fromString("5b666754-e217-4775-9c95-352ebb0673cb");

    @BeforeEach
    private void init(){
        documentRepository = mock(DocumentRepository.class);
        documentMapper = new DocumentMapperImpl();
        documentService = new DocumentServiceImpl(documentRepository);
    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.

    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved(){
        try {
            DocumentDTO documentDTO = createDummyDocument("name", DocumentStatus.APPROVED);
            when(documentService.createDocument(documentMapper.fromDTO(documentDTO))).thenReturn(new Document(DOCUMENT_UUID, "name", "random", 1, DocumentStatus.APPROVED));
            documentService.createDocument(documentMapper.fromDTO(documentDTO));
            when(documentRepository.findById(DOCUMENT_UUID)).thenReturn(Optional.of(new Document(DOCUMENT_UUID, "name", "random", 1, DocumentStatus.APPROVED)));
            documentService.updateDocument(documentMapper.fromDTO(DOCUMENT_UUID, documentDTO));
            fail();
        } catch (DocumentException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("The document has been already approved", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());
        }
    }

    private DocumentDTO createDummyDocument(String name, DocumentStatus documentStatus) {
        return DocumentDTO.builder().name(name).documentId(DOCUMENT_UUID).status(documentStatus).text("random").priority(1).build();
    }
}
