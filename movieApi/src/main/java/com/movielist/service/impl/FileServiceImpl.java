package com.movielist.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import com.movielist.service.IFileService;

@Service
public class FileServiceImpl implements IFileService{

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        
        String fileName = file.getOriginalFilename();
        
        String filePath = path + File.separator + fileName; // File.separator enabables that path and fileName can be appended.
        
        File mainFile = new File(path);
        
        if(!mainFile.exists()){
            mainFile.mkdir();
        }
        
        Files.copy(file.getInputStream(), Paths.get(filePath));
        
        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
    
}
