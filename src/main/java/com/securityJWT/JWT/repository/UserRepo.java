package com.securityJWT.JWT.repository;

import com.securityJWT.JWT.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String username);
}
