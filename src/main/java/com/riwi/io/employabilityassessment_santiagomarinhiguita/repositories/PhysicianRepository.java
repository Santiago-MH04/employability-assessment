package com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    //Atributos de PhysicianRepository
    //Constructores de PhysicianRepository
    //Asignadores de atributos de PhysicianRepository (setters)
    //Lectores de atributos de PhysicianRepository (getters)
        //Métodos de PhysicianRepository
    @Query("select p from Physician p where p.speciality =?1")
    public List<Physician> findPhysiciansBySpeciality(String specialityName);

    @Query("select p from Physician p left join p.appointments")
    public List<Physician> findPhysiciansBundledWithAppointments();


}
