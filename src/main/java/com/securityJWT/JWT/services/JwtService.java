package com.securityJWT.JWT.services;


import com.securityJWT.JWT.entity.UserEntity;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {


    private long expiration_minutes = 30;
    private String secretKey = "clave segura 12344";


    public String generateToken(UserEntity user, Map<String, Object> extraClaims) {

        //para guardar la hora en la que se emite en milisegundos
        Date issuedAt = new Date(System.currentTimeMillis());
        //para establecer cuanto tiempo se le va a poner a la expiración
        Date expiration = new Date(issuedAt.getTime() + (expiration_minutes * 60 * 1000));

        //construir y retornar el jwt como string
        return  Jwts.builder()
                .header().type("JWT")
                .and()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                //generar y agregar la firma al jwt
                .signWith(generateKey(),Jwts.SIG.HS256)
                //termina la construcción del jwt y lo transforma en string
                .compact();
    }



    //general la firma, basada en una secret key definida al inicio
    private SecretKey generateKey() {

        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
