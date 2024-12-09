package com.riwi.io.employabilityassessment_santiagomarinhiguita.security.filter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Usager;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.riwi.io.employabilityassessment_santiagomarinhiguita.security.JWTTokenConfig.*;

@NoArgsConstructor(force = true)    //Para que sí o sí genere un constructor vacío
@AllArgsConstructor
    /*@RequiredArgsConstructor*/    //Sólo crea constructores con los atributos declarados como final, también lo agrupa el @Data
@Builder    //Para generar instancias de Customer según nuestras necesidades /*@EqualsAndHashCode(exclude = ) */ //Para evitar un StackOverFlowError regordo
@Data
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
        //Atributos de JWTAuthenticationFilter
    private AuthenticationManager authenticationManager;

    //Constructores de JWTAuthenticationFilter
    //Asignadores de atributos de JWTAuthenticationFilter (setters)
    //Lectores de atributos de JWTAuthenticationFilter (getters)
        //Métodos de JWTAuthenticationFilter
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Usager usager = null;
        String name = null;
        String password = null;
            //Deserializar el JSON de respuesta para obtener un objeto Java
        try {
            usager = new ObjectMapper().readValue(request.getInputStream(), Usager.class);
            name = usager.getName();
            password = usager.getPassword();

        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            //Aquí el token debe validar usuario y contraseña, para iniciar sesión
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(name, password);
        return this.authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
            //Obtener la llave de seguridad
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String username = user.getUsername();

        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        Claims claims = Jwts.claims()
                            .add("authorities", new ObjectMapper().writeValueAsString(roles))
                            .add("username", username)  //Es un poco redundante, porque el username se pasa al token en el método de abajo
                            .build();
            /*claims.put("authorities", roles);*/

        String jwsToken = Jwts.builder()
                              .subject(username)
                              .claims(claims)
                              .issuedAt(Date.valueOf(LocalDate.now()))
                              .expiration(new Date(System.currentTimeMillis() + 3600000))
                              .signWith(SECRET_KEY)
                              .compact();

            //Enviar el token a la vista
        response.addHeader(HEADER_AUTHORISATION, PREFIX_TOKEN + jwsToken);
            //Enviar el token como respuesta JSON
        Map<String, String> body = new HashMap<>();
            body.put("token", jwsToken);
            body.put("username", username); //El username viaja dentro del token
            body.put("messaage", String.format("Hola %s, haz iniciado sesión con éxito", username));
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
            body.put("message", "Error en la autenticación: nombre de usuario o contraseña incorrectos");
            body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(CONTENT_TYPE);
    }
}
