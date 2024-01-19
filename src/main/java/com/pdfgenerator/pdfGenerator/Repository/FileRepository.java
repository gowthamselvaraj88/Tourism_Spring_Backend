package com.pdfgenerator.pdfGenerator.Repository;

import com.pdfgenerator.pdfGenerator.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
