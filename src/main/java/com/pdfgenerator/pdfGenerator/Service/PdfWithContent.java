package com.pdfgenerator.pdfGenerator.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class PdfWithContent {

    public void getPdf(String path, String content){
        File newFile = new File("D:\\NewPdf");
        System.out.println("File created");
        try(FileOutputStream fos = new FileOutputStream(newFile + "/" + path);
            PdfWriter writer = new PdfWriter(fos);
            PdfDocument pdf = new PdfDocument(writer)){
            Document  document = new Document(pdf);

            document.add(new Paragraph(content));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public byte[] openPdf(String outputPath) throws IOException {
        return Files.readAllBytes(new File(outputPath).toPath());
    }
}
