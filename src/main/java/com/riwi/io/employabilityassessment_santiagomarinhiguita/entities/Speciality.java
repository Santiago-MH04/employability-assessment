package com.riwi.io.employabilityassessment_santiagomarinhiguita.entities;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.utils.SpecialityBranch;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "specialities")
@NoArgsConstructor(force = true)    //Para que sí o sí genere un constructor vacío
@AllArgsConstructor
@RequiredArgsConstructor
//Sólo crea constructores con los atributos declarados como final, también lo agrupa el @Data
@Builder    //Para generar instancias de Appointment según mis necesidades
@Data
public class Speciality {
        //Atributos de Speciality
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "speciality_name")
    @Enumerated(EnumType.STRING)
    private SpecialityBranch specialityName;

    //Constructores de Speciality
    //Asignadores de atributos de Speciality (setters)
    //Lectores de atributos de Speciality (getters)
    //Métodos de Speciality
}
