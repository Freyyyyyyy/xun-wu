package com.cpt202.xunwu.controller;

import com.cpt202.xunwu.service.ViewProdInfoService;
import com.cpt202.xunwu.bean.ComPage;
import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.model.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewProdInfoController {
    
    @Autowired
    private ViewProdInfoService searchProdService;
    
    @GetMapping(value = "cdSearch")
    public ComPage cdSearch(@RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "6") int size,
                            @RequestParam(name = "sortC", defaultValue = "0") int sortC,
                            @RequestParam(name = "keyword", required = false) String keyword,                       
                            @RequestParam(name = "prodCategoryId", required = false) Integer prodCategoryId,
                            @RequestParam(name = "dateStart", required = false) Date dateStart,
                            @RequestParam(name = "dateEnd", required = false) Date dateEnd,
                            @RequestParam(name = "priceStart", required = false) Double priceStart,
                            @RequestParam(name = "priceEnd", required = false) Double priceEnd,
                            @RequestParam(name = "sizeStart", required = false) Long sizeStart,
                            @RequestParam(name = "sizeEnd", required = false) Long sizeEnd){
        
        Page<Product> CsProdPage = searchProdService.cdSearch(page, size, sortC, keyword, prodCategoryId, dateStart, dateEnd, priceStart, priceEnd, sizeStart, sizeEnd);                        
        ComPage result = new ComPage(page, size, CsProdPage.getTotalPages(), CsProdPage.getTotalElements(), CsProdPage.getContent());
        return result;
    }


    @GetMapping(value = "myProd")
    public ComPage myProd(@RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "6") int size,                       
                          HttpSession session){
        long userId = (long) session.getAttribute("userId");
        Page<Product> myProdPage = searchProdService.viewMyProd(page, size, userId);                        
        ComPage result = new ComPage(page, size, myProdPage.getTotalPages(), myProdPage.getTotalElements(), myProdPage.getContent());
        return result;
    }

    
    @GetMapping(value = "prodDetail")
    public ComResult prodDetail(@RequestParam(name = "prodId") long productId){

        return searchProdService.viewProdDetail(productId);
    }
}
