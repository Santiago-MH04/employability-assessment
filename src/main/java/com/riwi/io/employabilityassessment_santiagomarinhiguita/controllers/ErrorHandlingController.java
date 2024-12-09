package com.riwi.io.employabilityassessment_santiagomarinhiguita.controllers;


import com.riwi.io.employabilityassessment_santiagomarinhiguita.exceptions.IsOccupiedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandlingController {
    //Atributos de ErrorHandlingController
    //Constructores de ErrorHandlingController
    //Asignadores de atributos de ErrorHandlingController (setters)
    //Lectores de atributos de ErrorHandlingController (getters)
        //Métodos de ErrorHandlingController
    @ExceptionHandler(IsOccupiedException.class)    //Es decir, este método se enlaza únicamente a un error de tipo IsOccupiedException
    public ResponseEntity<com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.infrastructure.Error> appointmentOccupied(Exception e) { // ? es el comodín de los operadores diamante
        com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.infrastructure.Error error = new com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.infrastructure.Error();  //Incredibile como esto se construye y ejecuta automáticamente
            error.setErrorName("Error: cita ocupada");
            error.setMessage(e.getMessage());
            error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.setDate(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }
}
