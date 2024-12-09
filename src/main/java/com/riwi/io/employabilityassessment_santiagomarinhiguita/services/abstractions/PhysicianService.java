package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Physician;

import java.util.List;
import java.util.Optional;

public interface PhysicianService {
    //Atributos de PhysicianService
    //Constructores de PhysicianService
    //Asignadores de atributos de PhysicianService (setters)
    //Lectores de atributos de PhysicianService (getters)
        //Métodos de PhysicianService
    public List<Physician> findAll();
    public List<Physician> findBySpecialty(String specialty);
    public Optional<Physician> findById(Long id);
    public Physician save(Physician physician);
    public void deleteById(Long id);
}
