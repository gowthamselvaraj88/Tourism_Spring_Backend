package com.pdfgenerator.pdfGenerator.Controller;

import com.pdfgenerator.pdfGenerator.Entity.PdfContent;
import com.pdfgenerator.pdfGenerator.Service.MailSendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailService {

    @Autowired
    private MailSendingService mailService;


    @PostMapping("/sendmail")
    public String sendMail(@RequestBody PdfContent pdf){

        String subject = pdf.getDestination();
        String body = pdf.getName();

        mailService.sendMail("sasi57mano57@gmail.com",subject,body);

        return "Email sent";
    }
}
