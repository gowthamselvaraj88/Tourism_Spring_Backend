package com.pdfgenerator.pdfGenerator.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailSendingService {
    @Autowired
    public JavaMailSender mailSender;

    public void sendMail(String toEmail,String subject,String body){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("sasi77mano77@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);


        mailSender.send(mailMessage);

        System.out.println("Mail sent successfully");
    }

}
