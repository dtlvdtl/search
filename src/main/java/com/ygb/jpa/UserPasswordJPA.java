package com.ygb.jpa;


import com.ygb.controller.UserPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public  interface UserPasswordJPA extends
    JpaRepository<UserPasswordEntity,String>,
    JpaSpecificationExecutor<UserPasswordEntity>,
    Serializable {
    boolean existsByUserAndPassword(String user,String password);
    UserPasswordEntity findByUser(String user);
    int deleteByUser(String user);
    @Override
    UserPasswordEntity save(UserPasswordEntity entity);
}