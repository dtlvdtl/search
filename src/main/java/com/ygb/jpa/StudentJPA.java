package com.ygb.jpa;

import com.ygb.controller.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.io.Serializable;
import java.util.List;

public  interface StudentJPA extends
        JpaRepository<StudentEntity,String>,
        JpaSpecificationExecutor<StudentEntity>,
        Serializable {
        List<StudentEntity> findById(int id);
        List<StudentEntity> findByName(String name);
        List<StudentEntity> save(List<StudentEntity> student);
//        @Override
//        StudentEntity save(StudentEntity student);

        StudentEntity findByNameAndId(String name,int id);
        boolean existsById(int id);



}