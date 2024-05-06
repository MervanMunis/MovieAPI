package com.movielist.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    
    String uploadFile(String path, MultipartFile file) throws IOException;
    
    InputStream getResourceFile(String path, String fileName) throws FileNotFoundException;
}
