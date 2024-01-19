package com.pdfgenerator.pdfGenerator.Service;

import com.pdfgenerator.pdfGenerator.Entity.BookingEntity;
import com.pdfgenerator.pdfGenerator.Repository.BookingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {


    @Autowired
    public BookingRepository bookingRepository;
    public List<BookingEntity> getDetails(){

        List<BookingEntity> details = new ArrayList<>();
        bookingRepository.findAll().forEach(details::add);
        return details;
    }


    public void updateStatus(String status,Long userId){
       BookingEntity bookingEntity = new BookingEntity();
       bookingEntity.setStatus("Done");
    }

    public void  deleteUser(Long userId){
        bookingRepository.deleteById(userId);
    }
}
