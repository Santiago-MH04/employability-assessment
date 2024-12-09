package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    //Atributos de AppointmentService
    //Constructores de AppointmentService
    //Asignadores de atributos de AppointmentService (setters)
    //Lectores de atributos de AppointmentService (getters)
        //Métodos de AppointmentService
    public List<Appointment> findAll();
        public List<Appointment> findAllByPatientId(Long id);
        public List<Appointment> findAllByPhysicianId(Long id);
    public boolean isOccupied(LocalDateTime startTime, LocalDateTime endTime, Long physicianID);
    public Optional<Appointment> findById(Long id);
    public Appointment save(Appointment appointment);
    public void deleteById(Long id);
}
