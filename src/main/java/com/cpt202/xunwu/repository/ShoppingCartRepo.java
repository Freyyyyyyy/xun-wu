package com.cpt202.xunwu.repository;


import com.cpt202.xunwu.model.ShoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Integer>{
    
    ShoppingCart findUserNameByUserName(String UserName);
}
