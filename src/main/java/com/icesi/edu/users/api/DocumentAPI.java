package com.icesi.edu.users.api;

import com.icesi.edu.users.constant.dto.DocumentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/documents")
public interface DocumentAPI {

    @PostMapping
    public DocumentDTO createDocument(@RequestBody DocumentDTO documentDTO);

    @GetMapping("/{docId}")
    public DocumentDTO getDocument(@PathVariable UUID documentId);

    @GetMapping
    public List<DocumentDTO> getDocuments();

    @PostMapping("/{docId}")
    public DocumentDTO updateDocument(@PathVariable UUID documentId, @RequestBody DocumentDTO documentDTO);


}
