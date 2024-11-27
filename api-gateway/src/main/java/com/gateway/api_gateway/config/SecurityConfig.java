package com.gateway.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
Now the Oauth2.0 is added to authorize the routes, we need to request a token from auth server and then need
to pass with the request to the api gateway. use postman client's authorization with auth2.0 to obtain a token
and pass with the request.
token request path can be found by navigating to the keycloak admin UI -> realm settings -> show Oauth2.0 configuration
*/
@Configuration
public class SecurityConfig {
    private final String[] freeUrls= {"/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**","/swagger-resources/**",
            "/api-docs/**","/aggregate/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorize->authorize
                        .requestMatchers(freeUrls)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2->oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
}
