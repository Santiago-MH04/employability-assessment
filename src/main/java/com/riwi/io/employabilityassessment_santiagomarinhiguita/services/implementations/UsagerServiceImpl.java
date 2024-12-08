package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.implementations;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Role;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Usager;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.RoleRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.UsagerRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.UsagerService;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.utils.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsagerServiceImpl implements UsagerService {
        //Atributos de UsagerServiceImpl
    @Autowired
    private UsagerRepository repoUsager;
    @Autowired
    private RoleRepository repoRole;

    //Constructores de UsagerServiceImpl
    //Asignadores de atributos de UsagerServiceImpl (setters)
    //Lectores de atributos de UsagerServiceImpl (getters)
        //Métodos de UsagerServiceImpl
    @Override
    public List<Usager> findAll() {
        return this.repoUsager.findAll();
    }

    @Override
    public List<Usager> findAllActive() {
        return this.repoUsager.findAll()
                .stream()
                .filter(u -> u.getStatus().equals(UserStatus.ACTIVE))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usager> findById(Long id) {
        return this.repoUsager.findById(id);
    }

    @Override
    public Usager save(Usager usager) {
            //Cualquier Usager nuevo va a tener asociado el rol de paciente
        Optional<Role> rolePatient = this.repoRole.findByRoleName("ROLE_PATIENT");
        rolePatient.ifPresent(r -> usager.getRoles().add(r));
        return this.repoUsager.save(usager);
    }

    @Override
    public void deleteById(Long id) {
        this.repoUsager.findById(id).ifPresent(u -> {
            u.setStatus(UserStatus.INACTIVE);
        });
    }
}
