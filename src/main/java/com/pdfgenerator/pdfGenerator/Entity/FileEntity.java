package com.pdfgenerator.pdfGenerator.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "files")
public class FileEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long folderId;
    private String fileName;
    private String filePath;

    public FileEntity() {
    }

    public FileEntity(Long id, Long folderId, String fileName, String filePath) {
        super();
        this.id = id;
        this.folderId = folderId;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
