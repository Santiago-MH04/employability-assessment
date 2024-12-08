package com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    //Atributos de RoleRepository
    //Constructores de RoleRepository
    //Asignadores de atributos de RoleRepository (setters)
    //Lectores de atributos de RoleRepository (getters)
        //Métodos de RoleRepository
    public Optional<Role> findByRoleName(String name);
}
