package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.implementations;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Physician;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.PhysicianRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhysicianServiceImpl implements PhysicianService {
        //Atributos de PhysicianServiceImpl
    @Autowired
    private PhysicianRepository repoPhysician;

    //Constructores de PhysicianServiceImpl
    //Asignadores de atributos de PhysicianServiceImpl (setters)
    //Lectores de atributos de PhysicianServiceImpl (getters)
        //Métodos de PhysicianServiceImpl
    @Override
    public List<Physician> findAll() {
        return this.repoPhysician.findAll();
    }

    @Override
    public List<Physician> findBySpecialty(String specialty) {
        return this.repoPhysician.findPhysiciansBySpeciality(specialty.toUpperCase());
    }

    @Override
    public Optional<Physician> findById(Long id) {
        return this.repoPhysician.findById(id);
    }

    @Override
    public Physician save(Physician physician) {
        return this.repoPhysician.save(physician);
    }

    @Override
    public void deleteById(Long id) {
        this.repoPhysician.deleteById(id);
    }
}
