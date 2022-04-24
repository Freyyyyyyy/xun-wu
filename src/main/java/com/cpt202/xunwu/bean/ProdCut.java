package com.cpt202.xunwu.bean;

public class ProdCut<T> {
    private long productId;
    private String productName;
    private String productPicture;
    private double productPrice;

    public ProdCut(long productId, String productName, String productPicture, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPicture = productPicture;
        this.productPrice = productPrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
