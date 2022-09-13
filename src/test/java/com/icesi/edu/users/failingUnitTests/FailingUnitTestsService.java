package com.icesi.edu.users.failingUnitTests;

import com.icesi.edu.users.repository.DocumentRepository;
import com.icesi.edu.users.service.DocumentService;
import com.icesi.edu.users.service.impl.DocumentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FailingUnitTestsService {

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    private void init(){
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    //Implementar la logica para que cuando un documento con estado approved se intente actualizar se lanze un error y realizar la prueba.

    @Test
    public void exceptionIsThrownWhenTryingToUpdateDocumentWithStatusApproved(){

        fail();
    }


}
