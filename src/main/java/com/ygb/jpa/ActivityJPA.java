
package com.ygb.jpa;

import com.ygb.controller.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.io.Serializable;
import java.util.List;

public  interface ActivityJPA extends
        JpaRepository<ActivityEntity,String>,
        JpaSpecificationExecutor<ActivityEntity>,
        Serializable {
        List<ActivityEntity> findByAcid(int acid);
        List<ActivityEntity> findByYgs(float ygs);
        List<ActivityEntity> save(List<ActivityEntity> ygs);
//        ActivityEntity save(ActivityEntity ygs);
        int deleteByAcidAndAcNameAndTime(int acid,String acName,String time);
        List<ActivityEntity> findByAcName(String acName);


}