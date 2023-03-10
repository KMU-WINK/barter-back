package com.barter.barter.data.repository;

import com.barter.barter.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    long deleteByUser_id(String user_id);
    UserEntity findByUser_id(String user_id);
}
