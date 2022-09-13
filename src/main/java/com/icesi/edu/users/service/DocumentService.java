package com.icesi.edu.users.service;

import com.icesi.edu.users.model.Document;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface DocumentService {

    Document createDocument(@RequestBody Document document);


    Document getDocument(@PathVariable UUID documentId);


    List<Document> getDocuments();


    Document updateDocument(Document document);
}
