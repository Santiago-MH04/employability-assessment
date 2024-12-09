package com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain;

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
@Builder    //Para generar instancias de Usager según mis necesidades
@EqualsAndHashCode
@Data
public class Usager {
        //Atributos de Usager
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @EqualsAndHashCode.Exclude
    private String comments;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany
    private List<Role> roles;   //No es necesario aplicar bidireccionalidad en la relación
    @OneToMany
    @EqualsAndHashCode.Exclude
    private List<Appointment> appointments;

        //Constructores de Usager
    @PrePersist
    public void prePersist() {
        this.roles = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.setStatus(UserStatus.ACTIVE);
    }

        //Asignadores de atributos de Usager (setters)
    public void setId(Long id) {
        this.id = id;
    }
        public void setName(String name) {
            this.name = name;
        }
            public void setEmail(String email) {
                this.email = email;
            }
                public void setComments(String comments) {
                    this.comments = comments;
                }
                    public void setPassword(String password) {
                        this.password = password;
                    }
                        public void setStatus(UserStatus status) {
                            this.status = status;
                        }
                            public void setRoles(List<Role> roles) {
                                this.roles = roles;
                            }
                                public void setAppointments(List<Appointment> appointments) {
                                    this.appointments = appointments;
                                }

        //Lectores de atributos de Usager (getters)
    public Long getId() {
        return this.id;
    }
        public String getName() {
            return this.name;
        }
            public String getEmail() {
                return this.email;
            }
                public String getComments() {
                    return this.comments;
                }
                    public String getPassword() {
                        return this.password;
                    }
                        public UserStatus getStatus() {
                            return this.status;
                        }
                            public List<Role> getRoles() {
                                return this.roles;
                            }
                                public List<Appointment> getAppointments() {
                                    return this.appointments;
                                }

    //Métodos de Usager
}
