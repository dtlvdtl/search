package com.ygb.jpa;


import com.ygb.controller.ActivityEntity;
import com.ygb.controller.OtherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface OtherJPA extends
        JpaRepository<OtherEntity,String>,
        JpaSpecificationExecutor<OtherEntity>,
        Serializable {
//        OtherEntity save(OtherEntity otehr);
        OtherEntity findByOtherName(String name);

}
