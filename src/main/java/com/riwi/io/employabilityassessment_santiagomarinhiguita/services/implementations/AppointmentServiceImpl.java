package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.implementations;


import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Appointment;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.AppointmentRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
        //Atributos de AppointmentServiceImpl
    @Autowired
    private AppointmentRepository repoAppointment;

    //Constructores de AppointmentServiceImpl
    //Asignadores de atributos de AppointmentServiceImpl (setters)
    //Lectores de atributos de AppointmentServiceImpl (getters)
        //Métodos de AppointmentServiceImpl
    @Override
    public List<Appointment> findAll() {
        return this.repoAppointment.findAll();
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return this.repoAppointment.findById(id);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return this.repoAppointment.save(appointment);
    }

    @Override
    public void deleteById(Long id) {
        this.repoAppointment.deleteById(id);
    }
}
