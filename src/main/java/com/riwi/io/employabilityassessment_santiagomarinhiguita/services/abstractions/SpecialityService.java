package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Speciality;

import java.util.List;
import java.util.Optional;

public interface SpecialityService {
    //Atributos de SpecialityService
    //Constructores de SpecialityService
    //Asignadores de atributos de SpecialityService (setters)
    //Lectores de atributos de SpecialityService (getters)
        //Métodos de SpecialityService
    public List<Speciality> findAll();
    public Optional<Speciality> findById(Long id);
    public void save(Speciality speciality);
    public void deleteById(Long id);
}
