package com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    //Atributos de AppointmentRepository
    //Constructores de AppointmentRepository
    //Asignadores de atributos de AppointmentRepository (setters)
    //Lectores de atributos de AppointmentRepository (getters)
    //Métodos de AppointmentRepository
}
