package com.arun.practice.onboardingdocumentscollectionservice.repo;

import com.arun.practice.onboardingdocumentscollectionservice.model.DocumentCollection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentCollectionRepo extends CrudRepository<DocumentCollection, String> {
        List<DocumentCollection> findByDriverId(String driverId);

        List<DocumentCollection> findByOnboardingStatus(String onboardingStatus);

        DocumentCollection save(DocumentCollection entity);

}
