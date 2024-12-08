package com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    //Atributos de PhysicianRepository
    //Constructores de PhysicianRepository
    //Asignadores de atributos de PhysicianRepository (setters)
    //Lectores de atributos de PhysicianRepository (getters)
    //Métodos de PhysicianRepository
}
