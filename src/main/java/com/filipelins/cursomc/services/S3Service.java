package com.filipelins.cursomc.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public void uploadFile(String filePath) {
		try {
			File file = new File(filePath);
			log.info("Iniciando upload");
			s3Client.putObject(new PutObjectRequest(bucketName, "teste.png", file));
			log.info("Upload finalizado");
		} catch (AmazonServiceException e) {
			log.info("AmazonServiceException: " + e.getErrorMessage());
			log.info("Status code: " + e.getErrorCode());
		} catch (AmazonClientException e) {
			log.info("AmazonClientException: " + e.getMessage());
		}
	}
}
