package com.icesi.edu.users.api;

import com.icesi.edu.users.dto.DocumentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/documents")
public interface DocumentAPI {

    @PostMapping()
    public DocumentDTO createDocument(@RequestBody DocumentDTO documentDTO);

    @GetMapping("/{documentId}")
    public DocumentDTO getDocument(@PathVariable UUID documentId);
    @GetMapping()
    public List<DocumentDTO> getDocuments();

    @PostMapping("/{documentId}")
    public DocumentDTO updateDocument(@PathVariable UUID documentId, @RequestBody DocumentDTO documentDTO);


}
