package com.securityJWT.JWT.config.security;

import com.securityJWT.JWT.util.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    //inyectamos en authenticationProveider que hicimos en el SecurityBeanInjector
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //desactivamos el cross site request forgery que es una vulnerabilidad
        httpSecurity.csrf(csrfConfig -> csrfConfig.disable())
                //indicamos que no vamos a utilizar seguridad por sesiones indicando que es stateless
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //indicamos el mecanismo de autenticacion que es el que creamos
                .authenticationProvider(authenticationProvider)
                //indicamos que endpoints van a necesitar autenticacion
                .authorizeHttpRequests(authConfig -> { authConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
                                        authConfig.requestMatchers(HttpMethod.GET,"/auth/public-acces").permitAll();
                                        //esta ruta esta por default en spring el /error
                                        authConfig.requestMatchers(HttpMethod.GET,"/error").permitAll();
                                        authConfig.requestMatchers(HttpMethod.GET,"/products").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
                                        authConfig.requestMatchers(HttpMethod.POST,"/products").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());
                                        //para denegar el acceso a cualquier otro endpoint que no este definido arriba
                                        authConfig.anyRequest().denyAll();
                });
        return httpSecurity.build();
    }
}
