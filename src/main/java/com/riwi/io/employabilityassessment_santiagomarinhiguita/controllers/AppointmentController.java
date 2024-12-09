package com.riwi.io.employabilityassessment_santiagomarinhiguita.controllers;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Appointment;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
        //Atributos de CLASE
    @Autowired
    private AppointmentService appointmentService;

    //Constructores de CLASE
    //Asignadores de atributos de CLASE (setters)
    //Lectores de atributos de CLASE (getters)
        //Métodos de CLASE
    @GetMapping
    public List<Appointment> getAllAppointments(){
        return this.appointmentService.findAll();
    }

    @GetMapping("/{patientID}")
    public List<Appointment> getAllAppointmentsByPatient(Long patientID){
        return this.appointmentService.findAllByPhysicianId(patientID);
    }
    @GetMapping("/{physicianID}")
    public List<Appointment> getAllAppointmentsByPhysician(Long physicianID){
        return this.appointmentService.findAllByPhysicianId(physicianID);
    }
}
