package com.cpt202.xunwu.service;

import javax.servlet.http.HttpSession;

import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.model.Product;
import com.cpt202.xunwu.repository.ProductRepo;
import com.cpt202.xunwu.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPublishService {
    
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;
    
    public ComResult publish(Product product, HttpSession session){
        ComResult result = new ComResult();

        try{
            product.setProductStatus(1);
            long userId = (long) session.getAttribute("userId");
            product.setProductPublishUserId(userId);
            product.setProductPublishUserName(userRepo.findById(userId).get().getUserName());
            
            Product newProduct = new Product();
            newProduct = product;
            productRepo.save(newProduct);

            result.setCode(200);
            result.setMessage("商品信息上传成功");
            return result;

        } catch (Exception e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
            return result;
        }
    }


    public ComResult nameTest(String prodName, HttpSession session){
        ComResult result = new ComResult();

        try{
            long userId = (long) session.getAttribute("userId");
            Product existProduct = productRepo.findProductByProductNameAndProductPublishUserId(prodName, userId);
            if(existProduct != null){
                result.setCode(422);
                result.setMessage("您已发布该商品, 请重新输入");
                return result;
            }
            
            session.setAttribute("prodName", prodName);
            result.setCode(200);
            result.setMessage("商品名称可用,请继续上传商品图片");
            result.setData(prodName);
            return result;

        } catch (Exception e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
            return result;
        }
    }
}
