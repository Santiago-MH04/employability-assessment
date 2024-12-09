package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.implementations;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Speciality;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.SpecialityRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Speciality> findAll() {
        return this.repoSpeciality.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Speciality> findById(Long id) {
        return this.repoSpeciality.findById(id);
    }

    @Override
    @Transactional
    public void save(Speciality speciality) {
        this.repoSpeciality.save(speciality);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.repoSpeciality.deleteById(id);
    }
}
