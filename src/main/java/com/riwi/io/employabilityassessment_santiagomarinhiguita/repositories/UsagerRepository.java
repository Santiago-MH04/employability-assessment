package com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsagerRepository extends JpaRepository<Usager,Long> {
    //Atributos de UsagerRepository
    //Constructores de UsagerRepository
    //Asignadores de atributos de UsagerRepository (setters)
    //Lectores de atributos de UsagerRepository (getters)
        //Métodos de UsagerRepository
    @Query("select u from Usager u left join u.appointments where u.id =?1")
    public Optional<Usager> findUsagerBundledWithAppointments(Long id);
}
