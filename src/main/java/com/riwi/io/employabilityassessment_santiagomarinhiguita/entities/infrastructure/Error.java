package com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.infrastructure;

import java.time.LocalDateTime;

public class Error {    //Esta clase DTO va a gestionar los errores, así que tú tranqui, mi hermano
        //Atributos de Error
    private String errorName;
    private String message;
    private int status;
    private LocalDateTime date; //Este es el que genera el timestamp


    //Constructores de Error
        //Asignadores de atributos de Error (setters)
    public void setErrorName(String error) {
        this.errorName = error;
    }
        public void setMessage(String message) {
            this.message = message;
        }
            public void setStatus(int status) {
                this.status = status;
            }
                public void setDate(LocalDateTime date) {
                    this.date = date;
                }

        //Lectores de atributos de Error (getters)
    public String getErrorName() {
        return this.errorName;
    }
        public String getMessage() {
            return this.message;
        }
            public int getStatus() {
                return this.status;
            }
                public LocalDateTime getDate() {
                    return this.date;
                }

    //Métodos de Error
}
