package com.arun.practice.onboardingdocumentscollectionservice.model;

import com.arun.practice.onboardingdocumentscollectionservice.dto.DocumentDTO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdAttribute;

import java.util.Date;
import java.util.List;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.USE_ATTRIBUTES;

@Document
@Data
public class DocumentCollection {
    @Version
    private long version;
    @Id
    @GeneratedValue(strategy = USE_ATTRIBUTES)
    @Field
    private String id;

    private List<DocumentDTO> documents;
    @IdAttribute
    private String driverId;
    @Field
    private String onboardingStatus;

    @CreatedDate
    private Date creationDate;

    @LastModifiedDate
    private Date lastModification;

    public void setId(String id){
        this.id = id;
    }

}
