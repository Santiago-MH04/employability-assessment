package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.implementations;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Usager;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.UsagerRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.utils.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JPAUserDetailsService implements UserDetailsService {
        //Atributos de JPAUserDetailsService
    @Autowired
    private UsagerRepository repoUsager;

    //Constructores de JPAUserDetailsService
    //Asignadores de atributos de JPAUserDetailsService (setters)
    //Lectores de atributos de JPAUserDetailsService (getters)
        //Métodos de JPAUserDetailsService
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usager> usagerOptional = this.repoUsager.findByName(username);
        if(usagerOptional.isEmpty()) {
            throw new UsernameNotFoundException(
                    String.format("El usuario %s no está registrado en nuestro sistema", username)
            );
        }
        Usager usager = usagerOptional.get();
        List<GrantedAuthority> grantedAuthorities = usager.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().toString()))
                .collect(Collectors.toList());

        boolean isActive = false;
        if(usager.getStatus().equals(UserStatus.ACTIVE)){
            isActive = true;
        }

        return new org.springframework.security.core.userdetails.User(
                usager.getName(),
                usager.getPassword(),
                isActive,
                true,
                true,
                true,
                grantedAuthorities
        );
    }
}
