package com.pdfgenerator.pdfGenerator.Controller;

import com.pdfgenerator.pdfGenerator.Entity.BookingEntity;
import com.pdfgenerator.pdfGenerator.Service.BookingService;
import com.pdfgenerator.pdfGenerator.Service.PdfSendServie;
import com.pdfgenerator.pdfGenerator.Service.SyncService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    public BookingService bookingService;

    @Autowired
    public PdfSendServie pdfSendServie;

    @Autowired
    public SyncService syncService;

    @PostMapping("/newcustomer")
    public void addUser(@RequestBody BookingEntity booking){
        bookingService.addUser(booking);
    }
    @RequestMapping(method= RequestMethod.PUT, value="/users/{userId}")
    public void addBookings(@PathVariable Long userId, @RequestBody BookingEntity booking, HttpServletResponse response) throws IOException {

        bookingService.updateBooking(userId, booking,response);


    }
}
