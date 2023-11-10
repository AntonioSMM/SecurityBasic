package com.securityJWT.JWT.controller;

import com.securityJWT.JWT.dto.AuthenticationRequest;
import com.securityJWT.JWT.services.AuthenticationService;
import dto.AuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login (@RequestBody @Valid AuthenticationRequest authRequest){

        AuthenticationResponse jwtDTO = authenticationService.login(authRequest);

        return ResponseEntity.ok(jwtDTO);
    }

    @GetMapping("/public-acces")
    public String publicAcces(){
        return "ruta publica";
    }

}
