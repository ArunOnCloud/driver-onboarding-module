package com.arun.practice.onboardingdocumentscollectionservice.service;

import com.arun.practice.onboardingdocumentscollectionservice.dto.DocumentCollectionDTO;
import com.arun.practice.onboardingdocumentscollectionservice.model.DocumentCollection;
import com.arun.practice.onboardingdocumentscollectionservice.repo.DocumentCollectionRepo;
import com.arun.practice.onboardingdocumentscollectionservice.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class DocumentCollectionServiceImpl {

    @Autowired
    DocumentCollectionRepo documentCollectionRepo;

    public boolean createDriverOnboaring(DocumentCollectionDTO documentCollectionDTO){
        log.info("document received: {}"+ documentCollectionDTO);

        DocumentCollection documentCollection = Utils.dtoToModel(documentCollectionDTO);
        documentCollection.setId(Utils.getUUID());
        DocumentCollection saved = documentCollectionRepo.save(documentCollection);
        log.info("Saved dto: {}",documentCollectionDTO);

        return true;

    }
    public boolean updateDriverOnboaringDto(DocumentCollectionDTO documentCollectionDTO){
        log.info("document received: {}"+ documentCollectionDTO);

        return true;
    }
    public boolean updateDriverOnboardingStatus(DocumentCollectionDTO documentCollectionDTO){
        log.info(" updating status:{}",documentCollectionDTO.getOnboardingStatus());
        //get
        return true;
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
