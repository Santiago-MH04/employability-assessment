package com.riwi.io.employabilityassessment_santiagomarinhiguita.entities;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.utils.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usagers")
@NoArgsConstructor(force = true)    //Para que sí o sí genere un constructor vacío
@AllArgsConstructor
@RequiredArgsConstructor    //Sólo crea constructores con los atributos declarados como final, también lo agrupa el @Data
@Builder    //Para generar instancias de Physician según mis necesidades
@Data
public class Usager {
        //Atributos de Usager
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String comments;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany
    private List<Role> roles;   //No es necesario aplicar bidireccionalidad en la relación
    @OneToMany
    private List<Appointment> appointments;

        //Constructores de Usager
    @PrePersist
    public void prePersist() {
        this.roles = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.setStatus(UserStatus.ACTIVE);
    }

    //Asignadores de atributos de Usager (setters)
    //Lectores de atributos de Usager (getters)
    //Métodos de Usager
}
