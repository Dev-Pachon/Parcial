package com.icesi.edu.users.mapper;

import com.icesi.edu.users.dto.DocumentDTO;
import com.icesi.edu.users.model.Document;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-13T15:39:45-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public Document fromDTO(DocumentDTO documentDTO) {
        if ( documentDTO == null ) {
            return null;
        }

        Document.DocumentBuilder document = Document.builder();

        document.documentId( documentDTO.getDocumentId() );
        document.name( documentDTO.getName() );
        document.text( documentDTO.getText() );
        document.priority( documentDTO.getPriority() );
        document.status( documentDTO.getStatus() );

        return document.build();
    }

    @Override
    public DocumentDTO fromDocument(Document document) {
        if ( document == null ) {
            return null;
        }

        DocumentDTO.DocumentDTOBuilder documentDTO = DocumentDTO.builder();

        documentDTO.documentId( document.getDocumentId() );
        documentDTO.name( document.getName() );
        documentDTO.text( document.getText() );
        documentDTO.priority( document.getPriority() );
        documentDTO.status( document.getStatus() );

        return documentDTO.build();
    }

    @Override
    public Document fromDTO(UUID documentId, DocumentDTO documentDTO) {
        if ( documentId == null && documentDTO == null ) {
            return null;
        }

        Document.DocumentBuilder document = Document.builder();

        if ( documentDTO != null ) {
            document.documentId( documentDTO.getDocumentId() );
            document.name( documentDTO.getName() );
            document.text( documentDTO.getText() );
            document.priority( documentDTO.getPriority() );
            document.status( documentDTO.getStatus() );
        }

        return document.build();
    }
}
