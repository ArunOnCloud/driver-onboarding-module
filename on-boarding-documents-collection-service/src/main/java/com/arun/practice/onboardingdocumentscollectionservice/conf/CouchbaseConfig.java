package com.arun.practice.onboardingdocumentscollectionservice.conf;


import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableCouchbaseRepositories(basePackages = { "com.arun.practice.onboardingdocumentscollectionservice" })
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    public static final String NODE_LIST = "localhost";
    public static final String BUCKET_NAME = "document-collection";
    public static final String BUCKET_USERNAME = "Administrator";
    public static final String BUCKET_PASSWORD = "123456";

    @Override
    public String getConnectionString() {
        return NODE_LIST;
    }

    @Override
    public String getUserName() {
        return BUCKET_USERNAME;
    }

    @Override
    public String getPassword() {
        return BUCKET_PASSWORD;
    }

    @Override
    public String getBucketName() {
        return BUCKET_NAME;
    }

    @Override
    public QueryScanConsistency getDefaultConsistency() {
        return QueryScanConsistency.REQUEST_PLUS;
    }

}