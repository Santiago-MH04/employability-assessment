package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Role;

import java.util.Optional;

public interface RoleService {
    //Atributos de RoleService
    //Constructores de RoleService
    //Asignadores de atributos de RoleService (setters)
    //Lectores de atributos de RoleService (getters)
        //Métodos de RoleService
    public Optional<Role> findByRoleName(String roleName);
}
