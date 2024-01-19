package com.pdfgenerator.pdfGenerator.Controller;

import com.pdfgenerator.pdfGenerator.Entity.BookingEntity;
import com.pdfgenerator.pdfGenerator.Entity.FolderEntity;
import com.pdfgenerator.pdfGenerator.Entity.PdfContent;
import com.pdfgenerator.pdfGenerator.Service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pdf")
public class PdfGeneratorController {

//    @Autowired
//    public PdfGeneratorService pdfService;
    private Long folderId;
    private String userName;
    @Autowired
    private PdfSendServie pdfSendServie;
    @Autowired
    private AdminService adminService;
        @Autowired
        private MailSendingService mailService;

    private final PdfGeneratorService pdfService;

    private final SyncService syncService;

    public PdfGeneratorController( PdfGeneratorService pdfService, SyncService syncService) {
        this.pdfService = pdfService;
        this.syncService = syncService;
    }

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generatePdf(@RequestBody PdfContent pdfContent,HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "content-diposition";
        String headerValue = "attachment; filename=pdf_.pdf";
        response.setHeader(headerKey,headerValue);

        int noOfRecords = 200;

        String userName = pdfContent.getName();
        String destination = pdfContent.getDestination();

    //Create New Folder
        File newFile = new File("D:\\"+userName);
        if (newFile.mkdir()){
            System.out.println("created");
        }else{
            System.out.println("Not Created");
        }

        String folderPath = String.valueOf(newFile);

        System.out.println("FolderPath :: " + folderPath);

        String path = newFile + "/" + userName + ".pdf";

        String fileName = userName + ".pdf";

    //Folder table values
        pdfService.add(folderPath,userName,fileName);

    //Id

        getFileById();


        System.out.println("method :: "+getFileById());

        pdfService.addFileDetails(folderId,fileName,path);

    //For Threads
        List<Integer> inputList = new ArrayList<>();

        for(int i=0;i<noOfRecords;i++){
            inputList.add(i);
        }

        System.out.println("Size :: "+inputList.size());

        pdfService.submitAsyncTask(path,userName,destination,inputList);

    //Email sending service
        String toEmail = "sasi77mano77@gmail.com";
        String subject = userName;
        String body = "body";

        pdfSendServie.senEmailWithPdf(toEmail,subject,body,path);

        //Open created pdf
        byte[] readPdf = syncService.readPdf(path);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentDispositionFormData(userName, userName);

        return new ResponseEntity<>(readPdf,headers, HttpStatus.OK);
    }

    @GetMapping("/getid")
    public ResponseEntity<List<Long>> getFileById(){
       List<Long> id = pdfService.getAllUsers();
        folderId = id.get(0);
       return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @PostMapping("/sync")
    public ResponseEntity<byte[]> syncTask(@RequestBody PdfContent pdfContent, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "content-diposition";
        String headerValue = "attachment; filename=pdf_.pdf";
        System.out.println(pdfContent);
        response.setHeader(headerKey,headerValue);

        String userName = pdfContent.getName();
        String destination = pdfContent.getDestination();
        File newFile = new File("D:\\PDFS2");
        if (newFile.mkdir()){
            System.out.println("created");
        }else{
            System.out.println("Not Created");
        }
        System.out.println(destination + userName + newFile);

        String path = newFile + "/" + userName + ".pdf";

        syncService.syncTask(pdfContent,response);

        syncService.submitSyncTask(path,userName,destination);


        //Email sending service
        String toEmail = "gowthams88830@gmail.com";
        String subject = userName;
        String body = "body";

        pdfSendServie.senEmailWithPdf(toEmail,subject,body,path);

        //Open created pdf
        byte[] readPdf = syncService.readPdf(path);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentDispositionFormData(userName, userName);

        return new ResponseEntity<>(readPdf,headers, HttpStatus.OK);
    }




}
