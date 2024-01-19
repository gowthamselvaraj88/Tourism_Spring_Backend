package com.pdfgenerator.pdfGenerator.Repository;

import com.pdfgenerator.pdfGenerator.Entity.PdfContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfRepository extends JpaRepository<PdfContent, Long> {

}
