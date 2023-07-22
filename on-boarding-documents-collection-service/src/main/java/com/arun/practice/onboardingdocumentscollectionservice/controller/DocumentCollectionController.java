package com.arun.practice.onboardingdocumentscollectionservice.controller;


import com.arun.practice.onboardingdocumentscollectionservice.dto.DocumentCollectionDTO;
import com.arun.practice.onboardingdocumentscollectionservice.dto.DocumentDTO;
import com.arun.practice.onboardingdocumentscollectionservice.exception.RequestValidationException;
import com.arun.practice.onboardingdocumentscollectionservice.exception.ServiceException;
import com.arun.practice.onboardingdocumentscollectionservice.exception.ValidationCode;
import com.arun.practice.onboardingdocumentscollectionservice.service.DocumentCollectionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    @PutMapping("document/update")
    public ResponseEntity<String> updateDriverDocument(@RequestBody DocumentCollectionDTO documentCollectionDTO){
        try {
            validateDocumentUpdateRequest(documentCollectionDTO);
            documentCollectionService.updateDriverDocuments(documentCollectionDTO);
            return new ResponseEntity<String>("Success", HttpStatus.CREATED);
        }catch (RequestValidationException e){
            return   new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (ServiceException e){
            log.error("Update document failed:{}",e);
            e.printStackTrace();
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Update document failed:{}",e);
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("document")
    public ResponseEntity<String> addDriverDocument(@RequestBody DocumentCollectionDTO documentCollectionDTO){

        try {
            validateDocumentAddRequest(documentCollectionDTO);
            documentCollectionService.addDriverDocument(documentCollectionDTO);
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        }catch (RequestValidationException e){
            return   new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (ServiceException e){
            log.error("Add document failed:{}",e);
            e.printStackTrace();
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Add document failed:{}",e);
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("status")
    public ResponseEntity<String> updateDriverOnboaringStatus(@RequestBody DocumentCollectionDTO documentCollectionDTO){
        try{
            validateStatusUpdateRequest(documentCollectionDTO);
            documentCollectionService.updateDriverOnboardingStatus(documentCollectionDTO);
            return new ResponseEntity<String>("update success", HttpStatus.OK);
        }catch (RequestValidationException e){
            return   new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (ServiceException e){
            log.error("Update status failed:{}",e);
            e.printStackTrace();
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Update status failed:{}",e);
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocumentCollectionDTO>> getAll(){
        return new ResponseEntity<>(documentCollectionService.listAll(),HttpStatus.OK);
    }

    private static void validateStatusUpdateRequest(DocumentCollectionDTO documentCollectionDTO) throws Exception{

        if(StringUtils.isBlank(documentCollectionDTO.getDriverId()) ){
           throw new RequestValidationException("Driver id is required",ValidationCode.DRIVER_ID_NOT_PRESENT);
        }
        if(StringUtils.isBlank(documentCollectionDTO.getOnboardingStatus()) ){
            throw new RequestValidationException("Status is required ", ValidationCode.PARAMETER_VALIDATION);
        }
    }
    private static void validateDocumentUpdateRequest(DocumentCollectionDTO documentCollectionDTO) throws Exception{

        if(StringUtils.isBlank(documentCollectionDTO.getDriverId()) ){
            throw new RequestValidationException("Driver id is required",ValidationCode.DRIVER_ID_NOT_PRESENT);
        }
        if(documentCollectionDTO.getDocuments() == null || CollectionUtils.isEmpty(documentCollectionDTO.getDocuments())){
            throw new RequestValidationException("At least one document is required ",ValidationCode.PARAMETER_VALIDATION);
        }
        for(DocumentDTO documentDTO : documentCollectionDTO.getDocuments()){
            if( StringUtils.isAnyBlank(documentDTO.getDocId(),documentDTO.getFileType(),documentDTO.getFileUrl())){
                throw new RequestValidationException("docid , fileUrl,filetyes are required ",ValidationCode.PARAMETER_VALIDATION);
            }
        }
    }
    private static void validateDocumentAddRequest(DocumentCollectionDTO documentCollectionDTO) throws Exception{

        if(StringUtils.isBlank(documentCollectionDTO.getDriverId()) ){
            throw new RequestValidationException("Driver id is required",ValidationCode.DRIVER_ID_NOT_PRESENT);
        }
        if(documentCollectionDTO.getDocuments() == null || CollectionUtils.isEmpty(documentCollectionDTO.getDocuments())){
            throw new RequestValidationException("At least one document is required ",ValidationCode.PARAMETER_VALIDATION);
        }
        for(DocumentDTO documentDTO : documentCollectionDTO.getDocuments()){
            if( StringUtils.isAnyBlank(documentDTO.getFileType(),documentDTO.getFileUrl())){
                throw new RequestValidationException(" fileUrl,filetype are required ",ValidationCode.PARAMETER_VALIDATION);
            }
        }
    }


}
