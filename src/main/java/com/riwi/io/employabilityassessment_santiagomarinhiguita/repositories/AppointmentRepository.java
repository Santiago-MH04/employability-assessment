package com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    //Atributos de AppointmentRepository
    //Constructores de AppointmentRepository
    //Asignadores de atributos de AppointmentRepository (setters)
    //Lectores de atributos de AppointmentRepository (getters)
        //Métodos de AppointmentRepository
    @Query("select a from Appointment a where a.")
    public List<Appointment> findByPhysician();
    @Query("select a from Appointment a")
    public List<Appointment> findByPatient();
}
