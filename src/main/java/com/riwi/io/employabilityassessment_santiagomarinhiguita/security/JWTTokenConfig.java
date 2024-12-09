package com.riwi.io.employabilityassessment_santiagomarinhiguita.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class JWTTokenConfig {
        //Atributos de JWTTokenConfig
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORISATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

    //Constructores de JWTTokenConfig
    //Asignadores de atributos de JWTTokenConfig (setters)
    //Lectores de atributos de JWTTokenConfig (getters)
    //Métodos de JWTTokenConfig
}
