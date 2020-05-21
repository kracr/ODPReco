package com.springboot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service

public class FileService {
	 @Value("${app.upload.dir:${user.dir}}")
	    public String uploadDir;

	    public void uploadFile(MultipartFile file) throws Exception {

	        try {
	            @SuppressWarnings("deprecation")
				Path copyLocation = Paths
	                .get(uploadDir + File.separator + StringUtils.clean(file.getOriginalFilename()));
	            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new Exception("Could not store file " + file.getOriginalFilename()
	                + ". Please try again!");
	        }
	    }
	}


