package com.securityJWT.JWT.repository;

import com.securityJWT.JWT.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity,Long> {
}
