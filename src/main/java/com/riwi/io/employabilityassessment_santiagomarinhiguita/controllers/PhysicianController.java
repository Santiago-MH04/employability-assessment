package com.riwi.io.employabilityassessment_santiagomarinhiguita.controllers;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Physician;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/physicians")
public class PhysicianController {
        //Atributos de PhysicianController
    @Autowired
    private PhysicianService physicianService;

    //Constructores de PhysicianController
    //Asignadores de atributos de PhysicianController (setters)
    //Lectores de atributos de PhysicianController (getters)
        //Métodos de PhysicianController
    @GetMapping()
    public List<Physician> getAllPhysicians(){
        return this.physicianService.findAll();
    }
        @GetMapping("/{speciality}")
        public List<Physician> getAllPhysiciansBySpeciality(@PathVariable("speciality") String speciality){
            return this.physicianService.findBySpecialty(speciality);
        }

    @PostMapping("/register")
    public ResponseEntity<?> storePhysician(@RequestBody Physician physician) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.physicianService.save(physician));
    }

    @PutMapping("/update/{id}") //Este método puede usarse para cambiar correo, o contraseña
    public ResponseEntity<?> updateUsager(@PathVariable Long id, @RequestBody Physician physician) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.physicianService.save(physician));
    }

    @DeleteMapping("/destroy/{id}")
    public ResponseEntity<?> destroyUsagerById(@PathVariable Long id) {
        Optional<Physician> physicianOptional = this.physicianService.findById(id);
        if (physicianOptional.isPresent()){
            Physician physician = physicianOptional.get();
            this.physicianService.deleteById(physician.getId());
            return ResponseEntity.ok(physician);
        }
        return ResponseEntity.notFound().build();
    }
}
