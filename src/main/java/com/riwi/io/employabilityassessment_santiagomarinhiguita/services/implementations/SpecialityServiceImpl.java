package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.implementations;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Speciality;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.SpecialityRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityServiceImpl implements SpecialityService {
        //Atributos de SpecialityServiceImpl
    @Autowired
    private SpecialityRepository repoSpeciality;

    //Constructores de SpecialityServiceImpl
    //Asignadores de atributos de SpecialityServiceImpl (setters)
    //Lectores de atributos de SpecialityServiceImpl (getters)
        //Métodos de SpecialityServiceImpl

    @Override
    public List<Speciality> findAll() {
        return this.repoSpeciality.findAll();
    }

    @Override
    public Optional<Speciality> findById(Long id) {
        return this.repoSpeciality.findById(id);
    }

    @Override
    public void save(Speciality speciality) {
        this.repoSpeciality.save(speciality);
    }

    @Override
    public void deleteById(Long id) {
        this.repoSpeciality.deleteById(id);
    }
}
