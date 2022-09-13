package com.icesi.edu.users.failingUnitTests;

import com.icesi.edu.users.error.exception.DocumentError;
import com.icesi.edu.users.error.exception.DocumentException;
import com.icesi.edu.users.repository.DocumentRepository;
import com.icesi.edu.users.service.DocumentService;
import com.icesi.edu.users.service.impl.DocumentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class FailingUnitTestsService {

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    @BeforeEach
    private void init(){
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.

    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved(){
        try {
           // documentService.updateDocument(docu)
            fail();
        } catch(DocumentException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getHttpStatus());
            assertNotNull(e.getError());
            DocumentError error = e.getError();
            assertEquals("Could not update the server", error.getMessage());
            assertEquals("CODE_03", error.getCode().name());

        }
    }


}
