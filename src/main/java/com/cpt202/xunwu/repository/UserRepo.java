package com.cpt202.xunwu.repository;

import com.cpt202.xunwu.model.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Long>{
        
   UserInfo findUserByUserName(String userName);
        
}
