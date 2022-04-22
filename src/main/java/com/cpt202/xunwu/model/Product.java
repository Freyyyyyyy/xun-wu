package com.cpt202.xunwu.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

//import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    
    //1>ebook 2>video 3>account
    private long productCategoryId;
    private long productPublishUserId;
    private String productCategoryName;
    private String productPublishUserName;

    private String productName;
    private String productPicture;

    //0>未审核 1>已审核上架 2>已锁定 3>已删除
    private int productStatus;

    private double productPrice;
    private long productSaleVolume;

    private String productFormat;
    private String productSize;
    private long productSizeDesc;

    private String productDescription;
    private String productDescriptionPicture;
    
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creatDate;

    //@CreatedBy
    //private long creatBy;

    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public long getProductCategoryId() {
        return productCategoryId;
    }
    public void setProductCategoryId(long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
    public long getProductPublishUserId() {
        return productPublishUserId;
    }
    public void setProductPublishUserId(long productPublishUserId) {
        this.productPublishUserId = productPublishUserId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductPicture() {
        return productPicture;
    }
    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }
    public int getProductStatus() {
        return productStatus;
    }
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public long getProductSaleVolume() {
        return productSaleVolume;
    }
    public void setProductSaleVolume(long productSaleVolume) {
        this.productSaleVolume = productSaleVolume;
    }
    public String getProductFormat() {
        return productFormat;
    }
    public void setProductFormat(String productFormat) {
        this.productFormat = productFormat;
    }
    public String getProductSize() {
        return productSize;
    }
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public String getProductDescriptionPicture() {
        return productDescriptionPicture;
    }
    public void setProductDescriptionPicture(String productDescriptionPicture) {
        this.productDescriptionPicture = productDescriptionPicture;
    }
    
    public String getProductCategoryName() {
        return productCategoryName;
    }
    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }
    public String getProductPublishUserName() {
        return productPublishUserName;
    }
    public void setProductPublishUserName(String productPublishUserName) {
        this.productPublishUserName = productPublishUserName;
    }
    public long getProductSizeDesc() {
        return productSizeDesc;
    }
    public void setProductSizeDesc(long productSizeDesc) {
        this.productSizeDesc = productSizeDesc;
    }
    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }
    public Date getCreatDate() {
        return creatDate;
    }
    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

}

