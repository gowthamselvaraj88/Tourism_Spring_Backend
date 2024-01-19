package com.pdfgenerator.pdfGenerator.Repository;

import com.pdfgenerator.pdfGenerator.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
