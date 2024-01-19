package com.pdfgenerator.pdfGenerator.Controller;

import com.pdfgenerator.pdfGenerator.Entity.PdfContent;
import com.pdfgenerator.pdfGenerator.Service.PdfWithContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/new")
public class WithContentController {
   @Autowired
    public PdfWithContent newpdf;

    @PostMapping("/contentpdf")
    public String contentPdf(@RequestBody PdfContent pdfContent) throws IOException {

        String names = pdfContent.getName();
        String content = pdfContent.getDestination();
        int mobile = pdfContent.getMobile();
        System.out.println(mobile);
        System.out.println(content);
        String path =  names + "_document.pdf";
        String outputPath = "D:\\NewPdf"+ "/" + path;

        newpdf.getPdf(path,content);

        newpdf.openPdf(outputPath);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentDispositionFormData(outputPath,outputPath);

        return "done";
    }
}
