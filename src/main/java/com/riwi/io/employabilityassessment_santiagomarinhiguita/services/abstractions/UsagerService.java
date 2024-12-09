package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Usager;

import java.util.List;
import java.util.Optional;

public interface UsagerService {
    //Atributos de CLASE
    //Constructores de CLASE
    //Asignadores de atributos de CLASE (setters)
    //Lectores de atributos de CLASE (getters)
        //Métodos de CLASE
    public List<Usager> findAll();
    public List<Usager> findAllActive();
    public Optional<Usager> findById(Long id);
    public Optional<Usager> findByIdEager(Long id);
    public Usager save(Usager usager);
    public Usager toComment(Long id, String comment);
    public void deleteById(Long id);
}
