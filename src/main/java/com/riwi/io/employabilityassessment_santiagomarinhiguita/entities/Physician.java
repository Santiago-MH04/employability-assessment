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
@EqualsAndHashCode
@Data
public class Physician {
        //Atributos de Physician
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(name = "shift_start")
    @EqualsAndHashCode.Exclude
    private LocalTime shiftStart;
    @Column(name = "shift_end")
    @EqualsAndHashCode.Exclude
    private LocalTime shiftEnd;

    @ManyToOne  //Varios médicos comparten una misma especialidad || No es necesario aplicar bidireccionalidad
    @EqualsAndHashCode.Exclude
    private Speciality speciality;
    @OneToMany
    @EqualsAndHashCode.Exclude
    private List<Appointment> appointments;

        //Constructores de Physician
    @PrePersist
    private void prePersist() {
        this.appointments = new ArrayList<>();
    }

        //Asignadores de atributos de Physician (setters)
    public void setId(Long id) {
        this.id = id;
    }
        public void setName(String name) {
            this.name = name;
        }
            public void setEmail(String email) {
                this.email = email;
            }
                public void setShiftStart(LocalTime shiftStart) {
                    this.shiftStart = shiftStart;
                }
                    public void setShiftEnd(LocalTime shiftEnd) {
                        this.shiftEnd = shiftEnd;
                    }
                        public void setSpeciality(Speciality speciality) {
                            this.speciality = speciality;
                        }
                            public void setAppointments(List<Appointment> appointments) {
                                this.appointments = appointments;
                            }

        //Lectores de atributos de Physician (getters)
    public Long getId() {
        return this.id;
    }
        public String getName() {
            return this.name;
        }
            public String getEmail() {
                return this.email;
            }
                public LocalTime getShiftStart() {
                    return this.shiftStart;
                }
                    public LocalTime getShiftEnd() {
                        return this.shiftEnd;
                    }
                        public Speciality getSpeciality() {
                            return this.speciality;
                        }
                            public List<Appointment> getAppointments() {
                                return this.appointments;
                            }

    //Métodos de Physician
}
