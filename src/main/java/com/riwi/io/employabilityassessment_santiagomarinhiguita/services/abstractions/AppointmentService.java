package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    //Atributos de AppointmentService
    //Constructores de AppointmentService
    //Asignadores de atributos de AppointmentService (setters)
    //Lectores de atributos de AppointmentService (getters)
        //Métodos de AppointmentService
    public List<Appointment> findAll();
    public Optional<Appointment> findById(Long id);
    public Appointment save(Appointment appointment);
    public void deleteById(Long id);
}
