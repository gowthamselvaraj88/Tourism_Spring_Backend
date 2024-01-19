package com.pdfgenerator.pdfGenerator.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pdf_content")
public class PdfContent {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String destination;
    @Column
    private int mobile;

    public PdfContent() {
    }

    public PdfContent(Long id,String name, String destination, int mobile) {
        super();
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }
}
