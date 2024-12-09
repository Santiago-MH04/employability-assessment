package com.riwi.io.employabilityassessment_santiagomarinhiguita.controllers;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Appointment;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Usager;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.exceptions.IsOccupiedException;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.AppointmentService;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.UsagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsagerController {
        //Atributos de UsagerController
    @Autowired
    private UsagerService usagerService;
    @Autowired
    private AppointmentService appointmentService;

    //Constructores de UsagerController
    //Asignadores de atributos de UsagerController (setters)
    //Lectores de atributos de UsagerController (getters)
        //Métodos de UsagerController
    @GetMapping()
    public List<Usager> getAllUsagers() {
        return this.usagerService.findAll();
    }
        @GetMapping("/active")
        public List<Usager> getAllActiveUsagers() {
            return this.usagerService.findAllActive();
        }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsagerById(@PathVariable Long id) {
        return ResponseEntity.ok(this.usagerService.findById(id));
    }
        @GetMapping("/show-appointments/{id}")
        public ResponseEntity<?> getUsagerBundled(@PathVariable Long id) {
            return ResponseEntity.ok(this.usagerService.findByIdEager(id));
        }

    @PostMapping("/register")
    public ResponseEntity<?> storeUsager(@RequestBody Usager usager) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(this.usagerService.save(usager));
    }

    @PostMapping("/book/{physicianID}/{patientID}")
    public ResponseEntity<?> bookAppointment(@RequestParam Long physicianID, @RequestParam Long patientID, @RequestParam LocalDateTime appointmentStartTime) {
        LocalDateTime appointmentEndTime = appointmentStartTime.plus(30L, ChronoUnit.MINUTES);
        List<Appointment> physicianAppointments = this.appointmentService.findAllByPhysicianId(physicianID);
        if(this.appointmentService.isOccupied(appointmentStartTime, appointmentEndTime, physicianID)) {
            throw new IsOccupiedException("El médico indicado ya tiene una cita asignada a esta hora");
        }
        Optional<Usager> usagerOptional = this.usagerService.findById(patientID);
        if(usagerOptional.isPresent()) {
            Appointment appointment = Appointment.builder()
                    .reason("Consulta de rutina")
                    .appointmentStartTime(appointmentStartTime)
                    .appointmentEndTime(appointmentEndTime)
                    .build();
            this.appointmentService.save(appointment);
            Usager usager = usagerOptional.get();
                usager.getAppointments().add(appointment);
            this.usagerService.save(usager);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Cita reservada con éxito");
    }

    @PutMapping("/update/{id}") //Este método puede usarse para cambiar correo, o contraseña
    public ResponseEntity<?> updateUsager(@PathVariable Long id, @RequestBody Usager usager) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(this.usagerService.save(usager));
    }

    @DeleteMapping("/destroy/{id}")
    public ResponseEntity<?> destroyUsagerById(@PathVariable Long id) {
        Optional<Usager> usagerOptional = this.usagerService.findById(id);
        if (usagerOptional.isPresent()){
            Usager usager = usagerOptional.get();
            this.usagerService.deleteById(usager.getId());
            return ResponseEntity.ok(usager);
        }
        return ResponseEntity.notFound().build();
    }
}
