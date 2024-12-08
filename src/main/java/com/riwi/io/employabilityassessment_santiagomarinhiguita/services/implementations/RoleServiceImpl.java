package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.implementations;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Role;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.RoleRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
        //Atributos de RoleServiceImpl
    @Autowired
    private RoleRepository roleRepository;

    //Constructores de RoleServiceImpl
    //Asignadores de atributos de RoleServiceImpl (setters)
    //Lectores de atributos de RoleServiceImpl (getters)
        //Métodos de RoleServiceImpl

    @Override
    public Optional<Role> findByRoleName(String name) {
        return this.roleRepository.findByRoleName(name.toUpperCase());
    }
}
