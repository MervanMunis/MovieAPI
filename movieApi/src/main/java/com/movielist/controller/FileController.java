package com.movielist.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.movielist.service.IFileService;

@RestController
@RequestMapping("/file")
public class FileController {
    
    private final IFileService fileService;
    
    @Value("${project.poster}")
    private String path;
    
    public FileController(IFileService fileService){
        this.fileService = fileService;
    }
        
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException{
        String uploadedFileName = fileService.uploadFile(path, file);
        return ResponseEntity.ok("File uploaded: " + uploadedFileName);
    }
    
    @GetMapping("/{fileName}")
    public void serverFileHandler(@PathVariable String fileName, HttpServletResponse response) throws FileNotFoundException, IOException{
        InputStream resourceFile = fileService.getResourceFile(path, fileName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resourceFile, response.getOutputStream());
    }
    
}
