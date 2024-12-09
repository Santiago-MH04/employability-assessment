package com.riwi.io.employabilityassessment_santiagomarinhiguita.controllers;

import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.Physician;
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
    private List<Physician> getAllPhysicians(){
        return this.physicianService.findAll();
    }
        @GetMapping("/{speciality}")
        private List<Physician> getAllPhysiciansBySpeciality(@PathVariable("speciality") String speciality){
            return this.physicianService.findBySpecialty(speciality);
        }

    @PostMapping("/register")
    private ResponseEntity<?> storePhysician(@RequestBody Physician physician) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.physicianService.save(physician));
    }

    @PutMapping("/update/{id}") //Este método puede usarse para cambiar correo, o contraseña
    private ResponseEntity<?> updateUsager(@PathVariable Long id, @RequestBody Physician physician) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.physicianService.save(physician));
    }

    @DeleteMapping("/destroy/{id}")
    private ResponseEntity<?> destroyUsagerById(@PathVariable Long id) {
        Optional<Physician> physicianOptional = this.physicianService.findById(id);
        if (physicianOptional.isPresent()){
            Physician physician = physicianOptional.get();
            /*this.physicianService.deleteById(physician.getId());*/
            this.physicianService.deleteById(id);
            return ResponseEntity.ok(physician);
        }
        return ResponseEntity.notFound().build();
    }
}
