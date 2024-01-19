package com.pdfgenerator.pdfGenerator.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "folder")
public class FolderEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String folderName;
    private String folderPath;

    public FolderEntity() {
    }

    public FolderEntity(Long id, String folderName, String folderPath) {
        super();
        this.id = id;
        this.folderName = folderName;
        this.folderPath = folderPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }
}
