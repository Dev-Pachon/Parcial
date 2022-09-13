package com.icesi.edu.users.service;

import com.icesi.edu.users.model.Document;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface DocumentService {

    public Document createDocument(@RequestBody Document document);


    public Document getDocument(@PathVariable UUID documentId);


    public List<Document> getDocuments();


    public Document updateDocument(Document document);
}
