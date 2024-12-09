package com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    //Atributos de AppointmentRepository
    //Constructores de AppointmentRepository
    //Asignadores de atributos de AppointmentRepository (setters)
    //Lectores de atributos de AppointmentRepository (getters)
        //Métodos de AppointmentRepository
    @Query("select a from Appointment a where a.usager = (select u from Usager u where u.id =?1)")
    public List<Appointment> findByPatientId(Long id);
    @Query("select a from Appointment a where a.physician = (select p from Physician p where p.id =?1)")
    public List<Appointment> findByPhysicianId(Long id);
    public boolean isOccupied(LocalDateTime startTime, LocalDateTime endTime, Long physicianID);
}
