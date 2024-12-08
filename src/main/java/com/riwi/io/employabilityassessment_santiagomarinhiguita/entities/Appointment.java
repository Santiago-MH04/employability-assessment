package com.riwi.io.employabilityassessment_santiagomarinhiguita.entities;

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
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
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
    //Asignadores de atributos de Appointment (setters)
    //Lectores de atributos de Appointment (getters)
    //Métodos de Appointment
}
