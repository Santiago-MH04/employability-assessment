package com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    //Atributos de SpecialityRepository
    //Constructores de SpecialityRepository
    //Asignadores de atributos de SpecialityRepository (setters)
    //Lectores de atributos de SpecialityRepository (getters)
    //Métodos de SpecialityRepository
}
