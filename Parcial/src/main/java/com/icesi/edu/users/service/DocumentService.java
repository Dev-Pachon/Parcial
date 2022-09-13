package com.icesi.edu.users.service;

import com.icesi.edu.users.model.Document;

import java.util.List;
import java.util.UUID;

public interface DocumentService {

    Document createDocument(Document document);


    Document getDocument(UUID documentId);


    List<Document> getDocuments();


    Document updateDocument(Document document);
}
