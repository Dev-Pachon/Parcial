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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FailingUnitTestsService {

    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;
    private DocumentService documentService;

    private final UUID uuid = UUID.randomUUID();;

    @BeforeEach
    private void init(){
        documentRepository = mock(DocumentRepository.class);
        documentMapper = new DocumentMapperImpl();
        documentService = new DocumentServiceImpl(documentRepository);
    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.

    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved(){

        Document document = new Document(uuid, "document1", "test text", 1, DocumentStatus.APPROVED);

        when(documentRepository.save(document)).thenReturn(document);
        when(documentRepository.findById(document.getDocumentId())).thenReturn(Optional.of(document));
        try {
            documentService.updateDocument(document);
            fail();
        } catch (DocumentException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("The document was approved", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());
        }
    }


}
