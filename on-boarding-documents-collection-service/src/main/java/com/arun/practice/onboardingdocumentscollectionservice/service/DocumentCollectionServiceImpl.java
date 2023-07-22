package com.arun.practice.onboardingdocumentscollectionservice.service;

import com.arun.practice.onboardingdocumentscollectionservice.dto.DocumentCollectionDTO;
import com.arun.practice.onboardingdocumentscollectionservice.dto.DocumentDTO;
import com.arun.practice.onboardingdocumentscollectionservice.exception.DocumentCollectionServiceException;
import com.arun.practice.onboardingdocumentscollectionservice.exception.ServiceException;
import com.arun.practice.onboardingdocumentscollectionservice.model.DocumentCollection;
import com.arun.practice.onboardingdocumentscollectionservice.repo.DocumentCollectionRepo;
import com.arun.practice.onboardingdocumentscollectionservice.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentCollectionServiceImpl {

    @Autowired
    DocumentCollectionRepo documentCollectionRepo;

    public boolean createDriverOnboaring(DocumentCollectionDTO documentCollectionDTO){
        log.info("document received: {}"+ documentCollectionDTO);
        try {
            DocumentCollection documentCollection = Utils.dtoToModel(documentCollectionDTO);
            documentCollection.setId(Utils.getUUID());
            DocumentCollection saved = documentCollectionRepo.save(documentCollection);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ServiceException(DocumentCollectionServiceException.DOCUMENT_SAVE_EXCEPTION,ex);
        }
    }
    public boolean updateDriverOnboaringDto(DocumentCollectionDTO documentCollectionDTO){
        log.info("document received: {}"+ documentCollectionDTO);
        DocumentCollection documentCollection = Utils.dtoToModel(documentCollectionDTO);
        documentCollectionRepo.save(documentCollection);
        return true;
    }
    public boolean updateDriverDocuments(DocumentCollectionDTO documentCollectionDTO){
        log.info("document received: {}"+ documentCollectionDTO);
        Optional<DocumentCollection> documentCollectionOptional = documentCollectionRepo.findById(documentCollectionDTO.getDriverId());
        if(documentCollectionOptional.isPresent()){
            DocumentCollection documentCollection = documentCollectionOptional.get();
            Map<String, DocumentDTO> documentDTOMap= documentCollectionDTO.getDocuments().stream()
                    .collect(Collectors.toMap(documentDTO -> documentDTO.getDocId(), documentDTO ->documentDTO));
            for(DocumentDTO documentDTO: documentCollection.getDocuments()){
                if(documentDTOMap.containsKey(documentDTO.getDocId())){
                    documentDTO.setFileType(documentDTOMap.get(documentDTO.getDocId()).getFileType());
                    documentDTO.setFileUrl(documentDTOMap.get(documentDTO.getDocId()).getFileUrl());
                }
            }

            documentCollectionRepo.save(documentCollection);
            return true;
        }else{
            log.error("Driver with driver id {} Not present",documentCollectionDTO.getDriverId());
            throw new ServiceException(DocumentCollectionServiceException.DRIVER_NOT_PRESENT_EXCEPTION,new Exception(documentCollectionDTO.getDriverId()+ "not present"));

        }

    }

    public boolean addDriverDocument(DocumentCollectionDTO documentCollectionDTO){
        log.info(" adding document:{}",documentCollectionDTO.getOnboardingStatus());
        Optional<DocumentCollection> documentCollectionOptional = documentCollectionRepo.findById(documentCollectionDTO.getDriverId());
        if(documentCollectionOptional.isPresent()){
            DocumentCollection documentCollection = documentCollectionOptional.get();
            for(DocumentDTO documentDTO :documentCollectionDTO.getDocuments()){
                documentDTO.setDocId(Utils.getUUID().toString());
                documentCollection.getDocuments().add(documentDTO);
            }
            documentCollectionRepo.save(documentCollection);
            return true;
        }else{
            log.error("Driver with driver id {} Not present",documentCollectionDTO.getDriverId());
            throw new ServiceException(DocumentCollectionServiceException.DRIVER_NOT_PRESENT_EXCEPTION,new Exception(documentCollectionDTO.getDriverId()+ "not present"));

        }
    }
    public boolean updateDriverOnboardingStatus(DocumentCollectionDTO documentCollectionDTO){
        log.info(" updating status:{}",documentCollectionDTO.getOnboardingStatus());
        Optional<DocumentCollection> documentCollectionOptional = documentCollectionRepo.findById(documentCollectionDTO.getDriverId());
        if(documentCollectionOptional.isPresent()){
            DocumentCollection documentCollection = documentCollectionOptional.get();
            documentCollection.setOnboardingStatus(documentCollectionDTO.getOnboardingStatus());
            documentCollectionRepo.save(documentCollection);
            return true;
        }else{
            log.error("Driver with driver id {} Not present",documentCollectionDTO.getDriverId());
            throw new ServiceException(DocumentCollectionServiceException.DRIVER_NOT_PRESENT_EXCEPTION,new Exception(documentCollectionDTO.getDriverId()+ "not present"));

        }

    }

    public List<DocumentCollectionDTO> listAll(){

        List<DocumentCollectionDTO> result=new LinkedList<>();
        Iterable<DocumentCollection> list =    documentCollectionRepo.findAll();
        log.info("Document received:{}",list);
        for(DocumentCollection documentCollection: list){
            result.add(Utils.modelTODto(documentCollection));
        }
        return result;
    }
}
