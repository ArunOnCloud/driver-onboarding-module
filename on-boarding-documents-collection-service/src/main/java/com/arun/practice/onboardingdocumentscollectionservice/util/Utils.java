package com.arun.practice.onboardingdocumentscollectionservice.util;

import com.arun.practice.onboardingdocumentscollectionservice.dto.DocumentCollectionDTO;
import com.arun.practice.onboardingdocumentscollectionservice.model.DocumentCollection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;


@Slf4j
public class Utils {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static DocumentCollectionDTO modelTODto(DocumentCollection documentCollection){
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String json = objectMapper.writeValueAsString(documentCollection);
            DocumentCollectionDTO documentCollectionDTO = objectMapper.readValue(json, DocumentCollectionDTO.class);
            return documentCollectionDTO;
        }catch (Exception e){
            e.printStackTrace();
            log.error("Conversion error {}",e.fillInStackTrace());
        }
        return null;

    }
    public static DocumentCollection dtoToModel(DocumentCollectionDTO documentCollectionDTO){
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String json = objectMapper.writeValueAsString(documentCollectionDTO);
            DocumentCollection documentCollection= objectMapper.readValue(json, DocumentCollection.class);
            return documentCollection;
        }catch (Exception e){
            e.printStackTrace();
            log.error("Conversion error {}",e.fillInStackTrace());
        }
        return null;
    }
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
}
