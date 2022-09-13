package com.icesi.edu.users.api;

import com.icesi.edu.users.dto.DocumentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/documents")
public interface DocumentAPI {

    @PostMapping
    DocumentDTO createDocument(@RequestBody DocumentDTO documentDTO);

    @GetMapping("/getDocument")
    DocumentDTO getDocument(@PathVariable UUID documentId);

    @GetMapping
    List<DocumentDTO> getDocuments();

    @PostMapping("/update")
    DocumentDTO updateDocument(@PathVariable UUID documentId, @RequestBody DocumentDTO documentDTO);


}
