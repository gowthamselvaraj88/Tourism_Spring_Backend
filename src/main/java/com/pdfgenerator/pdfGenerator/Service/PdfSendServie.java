package com.pdfgenerator.pdfGenerator.Service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PdfSendServie {

    private final JavaMailSender mailpdf;

    public PdfSendServie(JavaMailSender mailpdf) {
        this.mailpdf = mailpdf;
    }

    public void senEmailWithPdf(String toEmail, String subject, String body, String path){

        MimeMessage message = mailpdf.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("sasi77mano77@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body);

            FileSystemResource  resource = new FileSystemResource(new File(path));

            helper.addAttachment(resource.getFilename(), resource);

            mailpdf.send(message);

            System.out.println("Mail sent");
        }catch (MessagingException e){
            e.printStackTrace();
        }


    }
}
