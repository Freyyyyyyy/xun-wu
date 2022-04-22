package com.cpt202.xunwu.controller;

import javax.servlet.http.HttpSession;

import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.model.Product;
import com.cpt202.xunwu.service.FileService;
import com.cpt202.xunwu.service.ProductPublishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductPublishController {
    
    @Autowired
    private FileService fileService;

    @Autowired
    private ProductPublishService productPublishService;

    @PostMapping(value = "productPublish")
    public ComResult productPublish(
                          @RequestBody Product product,
                          HttpSession session){
        return productPublishService.publish(product,session);
    }


    @PostMapping("/uploadPic")
    public ComResult uploadFile(@RequestParam("file") MultipartFile file, HttpSession session){
        
        return fileService.storeFile(file, session);
    }

    
    @PostMapping("/productNameTest")
    public ComResult productNameTest(@RequestParam(name = "prodName") String prodName, HttpSession session){
        return productPublishService.nameTest(prodName, session);
    }
}

