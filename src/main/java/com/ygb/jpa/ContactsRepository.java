package com.ygb.jpa;

import com.ygb.controller.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String name);
}
