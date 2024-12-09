package com.riwi.io.employabilityassessment_santiagomarinhiguita.security;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.security.filter.JWTAuthenticationFilter;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.security.filter.JWTValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)    //Así, se puede asegurar un endpoint por anotaciones sobre el método
public class SpringSecurityConfig {
        //Atributos de SpringSecurityConfig
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    //Constructores de SpringSecurityConfig
    //Asignadores de atributos de SpringSecurityConfig (setters)
    //Lectores de atributos de SpringSecurityConfig (getters)
        //Métodos de SpringSecurityConfig
    @Bean   //Para poder inyectar luego, es como el @Produces de Jakarta
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return this.authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {  //Para poder autorizar el uso de nuestra aplicación
        return httpSecurity.authorizeHttpRequests(
            auth -> auth
                        //Los endpoints que puede consumir todo el mundo
                    /*.requestMatchers(HttpMethod.GET,"/api/users/**").permitAll()*/   //Aquí se ingresan las rutas que queremos proteger, asociadas a los métodos escritos
                    .requestMatchers(HttpMethod.POST,"/api/users/register").permitAll()
                        //Los endpoints que sólo puede consumir un usuario autenticado con rol ADMIN
                    .requestMatchers(HttpMethod.POST,"/api/users/**").hasAnyRole("ADMIN", "PATIENT")
                    .requestMatchers(HttpMethod.PUT,"/api/users/**").hasAnyRole("ADMIN", "PATIENT")
                    .requestMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyRole("ADMIN", "PATIENT")
                    .anyRequest().authenticated())
            .addFilter(new JWTAuthenticationFilter(this.authenticationManager()))
            .addFilter(new JWTValidationFilter(this.authenticationManager()))
            .csrf(config -> config.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(
                management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Porque la información de las sesiones se guarda en el token
            )
            .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.setAllowedOriginPatterns(Arrays.asList("*"));
            corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")
        );
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfig);

        return source;
    }
}
