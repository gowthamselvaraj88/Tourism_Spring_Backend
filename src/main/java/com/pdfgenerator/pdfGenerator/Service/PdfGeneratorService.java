package com.pdfgenerator.pdfGenerator.Service;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdfgenerator.pdfGenerator.Entity.FileEntity;
import com.pdfgenerator.pdfGenerator.Entity.FolderEntity;
import com.pdfgenerator.pdfGenerator.Repository.FileRepository;
import com.pdfgenerator.pdfGenerator.Repository.FolderRepository;
import com.pdfgenerator.pdfGenerator.Repository.PdfRepository;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.base.Stopwatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PdfGeneratorService {


    private final PdfRepository repo;

    @Autowired
    public FolderRepository folderRepo;
    @Autowired
    public FileRepository fileRepo;


    public PdfGeneratorService(PdfRepository repo) {
        this.repo = repo;
    }


    static int batchElementSize = 25;

    public static int calculateThreadSize(int noOfRecords) {

        int minThreadSize = 5;
        int maxThreadSize = 100;

        int calculatedThreadSize = Math.min(maxThreadSize, Math.max(minThreadSize, noOfRecords / 100));
        System.out.println("calculatedThreadSize :: "+calculatedThreadSize);
        return calculatedThreadSize;
    }

    public void submitAsyncTask(String newFile ,String userName, String destination,List<Integer> inputList){

        AtomicInteger atomicInteger = new AtomicInteger( 1);
        Stopwatch stopWatch = new Stopwatch();
        stopWatch.start();
        System.out.println("started");

        ExecutorService executorService = Executors.newFixedThreadPool(calculateThreadSize(inputList.size()));

        System.out.println("treads:: " +executorService);
        List<List<Integer>> batchList = ListUtils.partition(inputList, batchElementSize);

        List<List<Integer>> batchOutput = Collections.synchronizedList(new ArrayList<>());
        System.out.println("input:: " +inputList.size());
        System.out.println("batch:: " +batchList.size());
        System.out.println("batchout:: " +batchOutput.size());

        List<CompletableFuture<?>> triggeredList = new ArrayList<>();

        for(List<Integer> batch:batchList){
            CompletableFuture<List<Integer>> identify = CompletableFuture.supplyAsync(()->{
                try{
                  List<Integer>  a = batch;
                   // System.out.println("batchItem :: " + a);
                    for (int i=0;i<a.size();i++){
                        int count = atomicInteger.getAndIncrement();
                       // System.out.println("count::"+count);
                        Document document = new Document();
                        PdfWriter.getInstance(document,new FileOutputStream(newFile));

                        document.open();
                        document.add(new Header("Invoice","Invoice"));
                        document.add(new Paragraph("User Name"+userName));
                        document.add(new Paragraph( "Destination"+destination ));
                        document.close();
                    }

                }catch (DocumentException | FileNotFoundException e){
                    e.printStackTrace();
                }

                return null;
            },executorService);

            triggeredList.add(identify);
        }

        System.out.println("triggered list" + triggeredList.size());

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(triggeredList.toArray(new CompletableFuture[triggeredList.size()]));

        try{
            Void unused = voidCompletableFuture.get();
        }catch(InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }

        long elapsed = stopWatch.elapsed(TimeUnit.MILLISECONDS);
        //System.out.println("batch 2 ::" + batchOutput.size());
        System.out.println("Time Taken :: " + elapsed + "millisecond");
        System.out.println("PDF Created!");
    }

    public byte[] readPdf(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }

    public void add( String pathName,String folderName, String fileName){
        FolderEntity folder = new FolderEntity();
        FileEntity file = new FileEntity();
        folder.setFolderPath(pathName);
        folder.setFolderName(folderName);
        folderRepo.save(folder);
        file.setFolderId(folder.getId());
        Long folderId = folder.getId();
        addFileDetails(folderId,fileName,pathName);
    }

    public List<Long> getAllUsers(){

        List<FolderEntity> folderItems = folderRepo.findAll();

        return folderItems.stream().map(FolderEntity::getId).collect(Collectors.toList());
    }

    public void addFileDetails(Long folderId,String fileName, String pathName){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFolderId(folderId);
        fileEntity.setFileName(fileName);
        fileEntity.setFilePath(pathName);
        fileRepo.save(fileEntity);
    }




}

