package com.arun.practice.onboardingdocumentscollectionservice.util;

import com.arun.practice.onboardingdocumentscollectionservice.dto.DocumentCollectionDTO;
import com.arun.practice.onboardingdocumentscollectionservice.exception.DocumentCollectionServiceException;
import com.arun.practice.onboardingdocumentscollectionservice.exception.ServiceException;
import com.arun.practice.onboardingdocumentscollectionservice.model.DocumentCollection;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;


@Slf4j
public class Utils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static DocumentCollectionDTO modelTODto(DocumentCollection documentCollection){
        try {

            String json = objectMapper.writeValueAsString(documentCollection);
            DocumentCollectionDTO documentCollectionDTO = objectMapper.readValue(json, DocumentCollectionDTO.class);
            return documentCollectionDTO;
        }catch ( JsonProcessingException jsonMappingException ){
            jsonMappingException.printStackTrace();
            throw new ServiceException(DocumentCollectionServiceException.JSON_CONVERSION_EXCEPTION,jsonMappingException);
        } catch (Exception exception){
            throw new ServiceException(DocumentCollectionServiceException.JSON_CONVERSION_EXCEPTION,exception);
        }
    }
    public static DocumentCollection dtoToModel(DocumentCollectionDTO documentCollectionDTO){
        try {
            String json = objectMapper.writeValueAsString(documentCollectionDTO);
            DocumentCollection documentCollection= objectMapper.readValue(json, DocumentCollection.class);
            return documentCollection;
        }catch ( JsonProcessingException jsonMappingException ){
            jsonMappingException.printStackTrace();
            throw new ServiceException(DocumentCollectionServiceException.JSON_CONVERSION_EXCEPTION,jsonMappingException);

        } catch (Exception exception){
            throw new ServiceException(DocumentCollectionServiceException.JSON_CONVERSION_EXCEPTION,exception);
        }
    }
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
}
