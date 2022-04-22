package com.cpt202.xunwu.repository;

import com.cpt202.xunwu.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    
    Product findProductByProductNameAndProductPublishUserId(String prodName, long prodId);

}
