package com.arun.practice.documentsservice.service;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.findify.s3mock.S3Mock;
public class S3ServiceMock {

    public static void main(String[] args) {
        S3Mock api = new S3Mock.Builder().withPort(8001).withInMemoryBackend().build();
        api.start();

        /* AWS S3 client setup.
         *  withPathStyleAccessEnabled(true) trick is required to overcome S3 default
         *  DNS-based bucket access scheme
         *  resulting in attempts to connect to addresses like "bucketname.localhost"
         *  which requires specific DNS setup.
         */
        EndpointConfiguration endpoint = new EndpointConfiguration("http://localhost:8001", "us-west-2");
        AmazonS3 client = AmazonS3ClientBuilder
                .standard()
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(endpoint)
                .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
                .build();

        client.createBucket("testbucket");
        client.putObject("testbucket", "file/name", "contents");
        api.shutdown(); // kills the underlying actor system. Use api.stop() to just unbind the port.
    }
}
