package com.securityJWT.JWT.services;

import com.securityJWT.JWT.entity.UserEntity;
import com.securityJWT.JWT.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import dto.AuthenticationRequest;
import dto.AuthenticationResponse;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                                    authRequest.getUsername(), authRequest.getPassword());

        authenticationManager.authenticate(authToken);

        UserEntity user = userRepo.findByUsername(authRequest.getUsername()).get();

        String jwt = jwtService.generateToken(user,generateExtraClaims(user));
        AuthenticationResponse jwtDTO = new AuthenticationResponse(jwt);

        return jwtDTO;
    }

    private Map<String, Object> generateExtraClaims(UserEntity user) {

        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("username",user.getUsername());
        extraClaims.put("role",user.getRole().name());

        return extraClaims;
    }
}
