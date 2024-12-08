package com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsagerRepository extends JpaRepository<Usager,Long> {
    //Atributos de UsagerRepository
    //Constructores de UsagerRepository
    //Asignadores de atributos de UsagerRepository (setters)
    //Lectores de atributos de UsagerRepository (getters)
    //Métodos de UsagerRepository
}
