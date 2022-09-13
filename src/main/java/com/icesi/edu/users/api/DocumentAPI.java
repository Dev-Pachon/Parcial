package com.icesi.edu.users.api;

import com.icesi.edu.users.dto.DocumentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/document")
public interface DocumentAPI {

    @PostMapping
    DocumentDTO createDocument(@RequestBody DocumentDTO documentDTO);

    @GetMapping("/{documentId}")
    DocumentDTO getDocument(@PathVariable UUID documentId);

    @GetMapping("/{documents}")
    List<DocumentDTO> getDocuments();

    @PostMapping
    DocumentDTO updateDocument(@PathVariable UUID documentId, @RequestBody DocumentDTO documentDTO);


}
