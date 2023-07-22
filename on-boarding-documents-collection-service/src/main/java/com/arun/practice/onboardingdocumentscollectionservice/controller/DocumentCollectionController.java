package com.arun.practice.onboardingdocumentscollectionservice.controller;


import com.arun.practice.onboardingdocumentscollectionservice.dto.DocumentCollectionDTO;
import com.arun.practice.onboardingdocumentscollectionservice.service.DocumentCollectionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/document/collection/")
public class DocumentCollectionController {


    @Autowired
    DocumentCollectionServiceImpl documentCollectionService;

    @PostMapping("")
    public ResponseEntity<String> createDriverOnboaring(@RequestBody DocumentCollectionDTO documentCollectionDTO){
        documentCollectionService.createDriverOnboaring(documentCollectionDTO);
        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<String> updateDriverOnboaring(@RequestBody DocumentCollectionDTO documentCollectionDTO){

        documentCollectionService.updateDriverOnboaringDto(documentCollectionDTO);
        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
    }
    @PutMapping("status")
    public ResponseEntity<String> updateDriverOnboaringStatus(@RequestBody DocumentCollectionDTO documentCollectionDTO){

        documentCollectionService.updateDriverOnboardingStatus(documentCollectionDTO);
        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DocumentCollectionDTO>> getAll(){
        return new ResponseEntity<>(documentCollectionService.listAll(),HttpStatus.OK);
    }


}
