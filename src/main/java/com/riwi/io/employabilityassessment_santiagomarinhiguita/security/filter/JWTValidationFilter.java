package com.riwi.io.employabilityassessment_santiagomarinhiguita.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.security.SimpleGrantedAuthorityJSONCreator;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.riwi.io.employabilityassessment_santiagomarinhiguita.security.JWTTokenConfig.*;

public class JWTValidationFilter extends BasicAuthenticationFilter {
    //Atributos de JWTValidationFilter
        //Constructores de JWTValidationFilter
    public JWTValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    //Asignadores de atributos de JWTValidationFilter (setters)
    //Lectores de atributos de JWTValidationFilter (getters)
        //Métodos de JWTValidationFilter

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
            //Obtener el Token
        String header = request.getHeader(HEADER_AUTHORISATION);
        if(header == null || !header.startsWith(PREFIX_TOKEN)){
            chain.doFilter(request, response); //Para que siga ejecutando el método
            return; //Esto implica que nos salimos del método, vaina rara
        }
        String token = header.replace(PREFIX_TOKEN, "");
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            String username = claims.getSubject();
            /*String username = (String)claims.get("username");*/   //Porque en un ejercicio loco le metimos el username a los claims
            Object authoritiesClaims = claims.get("authorities");

                //Este error se debe solucionar con otra clase de configuración
            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                new ObjectMapper()
                .addMixIn(
                    SimpleGrantedAuthority.class,
                    SimpleGrantedAuthorityJSONCreator.class
                )
                .readValue(
                    authoritiesClaims.toString().getBytes(),
                    SimpleGrantedAuthority[].class
                )
            );

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                //Para manejar los roles tenemos que autenicarnos
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                //¿Todo salió bien?
            chain.doFilter(request, response);

        } catch (JwtException e) {
            Map<String, String> body = new HashMap<>();
                body.put("error", e.getMessage());
                body.put("message", "El token JWT es inválido");

            response.getWriter()
            .write(new ObjectMapper()
                .writeValueAsString(body)
            );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
