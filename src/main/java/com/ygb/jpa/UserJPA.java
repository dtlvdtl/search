package com.ygb.jpa;

        import com.ygb.controller.UserEntity;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

        import java.io.Serializable;
        import java.util.List;

public  interface UserJPA extends
        JpaRepository<UserEntity,Long>,
        JpaSpecificationExecutor<UserEntity>,
        Serializable{
        List<UserEntity> findByName(String name);
        List<UserEntity> findById(String id);
        List<UserEntity> findByActname(String actname);
        List<UserEntity> findByNameAndId(String name, String id);
        boolean existsByNameAndId(String name, String id);
}
