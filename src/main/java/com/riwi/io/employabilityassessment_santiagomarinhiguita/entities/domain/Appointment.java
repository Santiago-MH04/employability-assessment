package com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.utils.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "appointments")
@NoArgsConstructor(force = true)    //Para que sí o sí genere un constructor vacío
@AllArgsConstructor
@RequiredArgsConstructor    //Sólo crea constructores con los atributos declarados como final, también lo agrupa el @Data
@Builder    //Para generar instancias de Appointment según mis necesidades
@Data
public class Appointment {
        //Atributos de Appointment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
    @Column(name = "appointment_start_time")
    private LocalDateTime appointmentStartTime;
    @Column(name = "appointment_end_time")
    private LocalDateTime appointmentEndTime;
    private String diagnosis;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @OneToOne
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "usager_id")
    private Usager usager;
    @ManyToOne
    private Physician physician;

        //Constructores de Appointment
    @PrePersist
    public void prePersist() {
        this.status = AppointmentStatus.ACTIVE;
    }

    //Asignadores de atributos de Appointment (setters)
    //Lectores de atributos de Appointment (getters)
    //Métodos de Appointment
}
