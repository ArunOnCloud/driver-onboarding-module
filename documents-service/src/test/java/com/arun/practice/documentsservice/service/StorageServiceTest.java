package com.arun.practice.documentsservice.service;



import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootTest
//@Profile("test")
public class StorageServiceTest {

    @Autowired
    StorageService storageService;

    @Test
    @Ignore
    public void testStore() throws Exception{
//        File file = new File("./text.txt");
//        InputStream stream =  new FileInputStream(file);
//        MultipartFile multipartFileToSend = new MockMultipartFile("file", file.getName(), MediaType.TEXT_HTML_VALUE, stream);
//        storageService.store(multipartFileToSend,"adhar");


    }
}
