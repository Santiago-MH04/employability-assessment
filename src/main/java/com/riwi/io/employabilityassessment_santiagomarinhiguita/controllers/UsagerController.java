package com.riwi.io.employabilityassessment_santiagomarinhiguita.controllers;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Usager;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.UsagerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsagerController {
        //Atributos de UsagerController
    @Autowired
    private UsagerService usagerService;

    //Constructores de UsagerController
    //Asignadores de atributos de UsagerController (setters)
    //Lectores de atributos de UsagerController (getters)
        //Métodos de UsagerController
    @GetMapping()
    private List<Usager> getAllUsagers() {
        return this.usagerService.findAll();
    }
        @GetMapping("/active")
        private List<Usager> getAllActiveUsagers() {
            return this.usagerService.findAllActive();
        }

    @GetMapping("/{id}")
    private ResponseEntity<?> getUsagerById(@PathVariable Long id) {
        return ResponseEntity.ok(this.usagerService.findById(id));
    }
        @GetMapping("/show-appointments/{id}")
        private ResponseEntity<?> getUsagerBundled(@PathVariable Long id) {
            return ResponseEntity.ok(this.usagerService.findByIdEager(id));
        }

    @PostMapping("/register")
    private ResponseEntity<?> storeUsager(@RequestBody Usager usager) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(this.usagerService.save(usager));
    }

    @PutMapping("/update/{id}") //Este método puede usarse para cambiar correo, o contraseña
    private ResponseEntity<?> updateUsager(@PathVariable Long id, @RequestBody Usager usager) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(this.usagerService.save(usager));
    }

    @DeleteMapping("/destroy/{id}")
    private ResponseEntity<?> destroyUsagerById(@PathVariable Long id) {
        Optional<Usager> usagerOptional = this.usagerService.findById(id);
        if (usagerOptional.isPresent()){
            Usager usager = usagerOptional.get();
            this.usagerService.deleteById(usager.getId());
            return ResponseEntity.ok(usager);
        }
        return ResponseEntity.notFound().build();
    }
}
