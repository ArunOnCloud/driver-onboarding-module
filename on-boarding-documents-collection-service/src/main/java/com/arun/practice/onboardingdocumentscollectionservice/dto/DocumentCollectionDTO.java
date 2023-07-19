package com.arun.practice.onboardingdocumentscollectionservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class DocumentCollectionDTO {

    private List<DocumentDTO> documents;
    private String driverId;
    private String onboardingStatus;
    private long version;
}
