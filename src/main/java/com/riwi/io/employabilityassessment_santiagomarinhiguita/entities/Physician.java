package com.riwi.io.employabilityassessment_santiagomarinhiguita.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "physicians")
@NoArgsConstructor(force = true)    //Para que sí o sí genere un constructor vacío
@AllArgsConstructor
@RequiredArgsConstructor    //Sólo crea constructores con los atributos declarados como final, también lo agrupa el @Data
@Builder    //Para generar instancias de Physician según mis necesidades
@Data
public class Physician {
        //Atributos de Physician
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(name = "shift_start")
    private LocalTime shiftStart;
    @Column(name = "shift_end")
    private LocalTime shiftEnd;

    @ManyToOne  //Varios médicos comparten una misma especialidad || No es necesario aplicar bidireccionalidad
    private Speciality speciality;
    @OneToMany
    private List<Appointment> appointments;

        //Constructores de Physician
    @PrePersist
    private void prePersist() {
        this.appointments = new ArrayList<>();
    }

    //Asignadores de atributos de Physician (setters)
    //Lectores de atributos de Physician (getters)
    //Métodos de Physician
}
