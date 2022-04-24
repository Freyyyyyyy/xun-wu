package com.cpt202.xunwu.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.model.Product;
import com.cpt202.xunwu.model.ShoppingCart;
import com.cpt202.xunwu.service.ShoppingCartService;
import com.cpt202.xunwu.bean.ProdCut;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping(value = "saveCartInfo")
    public ComResult saveCartInfo(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.saveCartInfo(shoppingCart);
    }

    @PostMapping(value = "getProductId")
    public ComResult getProductId(@RequestParam(name = "userName") String userName){
        // System.out.println(way);
        return shoppingCartService.getProductId(userName);
    }

    @PostMapping(value = "getProductInfo")
    public ProdCut getProductInfo(@RequestParam(name = "AProductId") long AProductId){
        return shoppingCartService.findProductInfo(AProductId);

    }
    
}
