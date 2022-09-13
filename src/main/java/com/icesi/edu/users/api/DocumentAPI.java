package com.icesi.edu.users.api;

import com.icesi.edu.users.dto.DocumentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/documents")
public interface DocumentAPI {

    @PostMapping("/documents")
    DocumentDTO createDocument(@RequestBody DocumentDTO documentDTO);

    @GetMapping("/{documentId}")
    DocumentDTO getDocument(@PathVariable UUID documentId);

    @GetMapping("documents/:documentId")
    List<DocumentDTO> getDocuments();

    @PostMapping("/documents/:documentId")
    DocumentDTO updateDocument(@PathVariable UUID documentId, @RequestBody DocumentDTO documentDTO);


}
