package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.implementations;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Usager;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.UsagerRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.UsagerService;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.utils.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsagerServiceImpl implements UsagerService {
        //Atributos de UsagerServiceImpl
    @Autowired
    private UsagerRepository repoUsager;

    //Constructores de UsagerServiceImpl
    //Asignadores de atributos de UsagerServiceImpl (setters)
    //Lectores de atributos de UsagerServiceImpl (getters)
        //Métodos de UsagerServiceImpl
    @Override
    public List<Usager> findAll() {
        return this.repoUsager.findAll();
    }

    @Override
    public Optional<Usager> findById(Long id) {
        return this.repoUsager.findById(id);
    }

    @Override
    public Usager save(Usager usager) {
        return this.repoUsager.save(usager);
    }

    @Override
    public void deleteById(Long id) {
        this.repoUsager.findById(id).ifPresent(u -> {
            u.setStatus(UserStatus.INACTIVE);
        });
    }
}
