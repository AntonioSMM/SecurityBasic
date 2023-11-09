package com.securityJWT.JWT.controller;

import com.securityJWT.JWT.entity.ProductEntity;
import com.securityJWT.JWT.repository.ProductRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll() {
        List<ProductEntity> products = productRepo.findAll();

        if(products != null && !products.isEmpty()) {
            return ResponseEntity.ok(products);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<ProductEntity> createOne (@RequestBody @Valid ProductEntity product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepo.save(product));
    }
}
