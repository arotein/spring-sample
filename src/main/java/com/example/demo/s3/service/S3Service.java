package com.example.demo.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @SneakyThrows
    public void get(String key) {
        // key는 파일의 path를 의미
        S3Object object = amazonS3Client.getObject(bucket, key);
        InputStream delegateStream = object.getObjectContent().getDelegateStream();
        Files.copy(delegateStream, Paths.get("C:/sampleImg", object.getKey()), StandardCopyOption.REPLACE_EXISTING);
    }

    public void upload(MultipartFile file) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            amazonS3Client.putObject(bucket, "sample/" + file.getOriginalFilename(), file.getInputStream(), objectMetadata);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
