package com.cpt202.xunwu.service;

import org.springframework.stereotype.Service;

import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.property.FileProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;

@Service
public class FileService {
    private final Path fileStorageLocation; 

    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ComResult storeFile(MultipartFile file, HttpSession session) {
        ComResult result  =new ComResult<>();

        long Uid = (long) session.getAttribute("userId");
        String userId = Long.toString(Uid);
        String prodName = (String) session.getAttribute("prodName");
        String fileName = userId + prodName + StringUtils.cleanPath(file.getOriginalFilename());
        
        try {
            if(fileName.contains("..")) {
                result.setCode(422);
                result.setMessage("无效路径！");
                return result;
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            result.setCode(200);
            result.setMessage("图片已上传");
            result.setData(fileName);
            return result;
        } catch (IOException e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
            return result;
        }
    }

}
