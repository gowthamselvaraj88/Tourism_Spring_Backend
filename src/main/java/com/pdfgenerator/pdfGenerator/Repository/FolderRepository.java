package com.pdfgenerator.pdfGenerator.Repository;

import com.pdfgenerator.pdfGenerator.Entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FolderRepository extends JpaRepository<FolderEntity, Long> {
  //  @Query(value = "SELECT * FROM folder inner join files on folder.id = files.folderId",nativeQuery = true)
}
