package com.pdfgenerator.pdfGenerator.Service;

import com.google.common.base.Stopwatch;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdfgenerator.pdfGenerator.Entity.PdfContent;
import com.pdfgenerator.pdfGenerator.Repository.PdfRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SyncService {
    static int noOfRecords = 10;

    @Autowired
    public PdfRepository repo;
    public void syncTask(@RequestBody PdfContent pdfContent, HttpServletResponse response) {

        List<Integer> inputList = new ArrayList<>();
        List<Integer> outputList = new ArrayList<>();


        for(int i=0;i<noOfRecords;i++){
            inputList.add(i);
        }
        System.out.println(inputList.size());
//        String destination = pdfContent.getDestination();
//        String userName = pdfContent.getName();
//        File newFile = new File("D:\\PDFS2");
//        if (newFile.mkdir()){
//            System.out.println("created");
//        }else{
//            System.out.println("Not Created");
//        }
//        System.out.println(destination + userName + newFile);
//        submitAsyncTask(String.valueOf(newFile),userName,destination,inputList);
    }

    public void submitSyncTask(String newFile ,String userName, String destination){


        AtomicInteger atomicInteger = new AtomicInteger( 1);
        Stopwatch stopWatch = new Stopwatch();
        stopWatch.start();
        System.out.println("started");


        for(int i=1;i<=noOfRecords;i++){
            int count = i;
            try{
                Document document = new Document();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                PdfWriter.getInstance(document,new FileOutputStream( newFile ));
                System.out.println("new userName"+destination + userName);

                document.open();

                document.add(new Paragraph( "Destination :: " + destination  ));
                document.close();
            }catch (DocumentException | FileNotFoundException e){
                e.printStackTrace();
            }

        }

        long elapsed = stopWatch.elapsed(TimeUnit.MILLISECONDS);
        //System.out.println("batch 2 ::" + batchOutput.size());
        System.out.println("Time Taken :: " + elapsed + " millisecond");
        System.out.println("PDF Created!");


    }

    public byte[] readPdf(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }

}
