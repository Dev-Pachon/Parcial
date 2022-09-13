package com.icesi.edu.users.model;

import com.icesi.edu.users.constant.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table(name = "`user`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Id
    private UUID documentId;

    private String name;

    private String text;

    private int priority;

    private DocumentStatus status;

    @PrePersist
    public void generateId() {
        this.documentId = UUID.randomUUID();
    }

}
