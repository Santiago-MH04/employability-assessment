package com.riwi.io.employabilityassessment_santiagomarinhiguita.services.implementations;


import com.riwi.io.employabilityassessment_santiagomarinhiguita.entities.domain.Appointment;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.repositories.AppointmentRepository;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.services.abstractions.AppointmentService;
import com.riwi.io.employabilityassessment_santiagomarinhiguita.utils.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AppointmentServiceImpl implements AppointmentService {
        //Atributos de AppointmentServiceImpl
    @Autowired
    private AppointmentRepository repoAppointment;

    //Constructores de AppointmentServiceImpl
    //Asignadores de atributos de AppointmentServiceImpl (setters)
    //Lectores de atributos de AppointmentServiceImpl (getters)
        //Métodos de AppointmentServiceImpl
    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAll() {
        return this.repoAppointment.findAll();
    }
        @Override
        public List<Appointment> findAllByPatientId(Long id) {
            return this.repoAppointment.findByPatientId(id);
        }
        @Override
        public List<Appointment> findAllByPhysicianId(Long id) {
            return this.repoAppointment.findByPhysicianId(id);
        }

    @Override
    public boolean isOccupied(LocalDateTime startTime, LocalDateTime endTime, Long physicianID) {
        AtomicBoolean isOccupied = new AtomicBoolean(false);
        List<Appointment> physicianAppointments = this.findAllByPhysicianId(physicianID);
        physicianAppointments.stream().forEach(
            pa -> {
                if(pa.getAppointmentStartTime().isAfter(startTime) &&
                        pa.getAppointmentEndTime().isBefore(endTime) &&
                          pa.getStatus().equals(AppointmentStatus.ACTIVE)) {
                    isOccupied.set(true);
                }
            }
        );
        return isOccupied.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Appointment> findById(Long id) {
        return this.repoAppointment.findById(id);
    }


    @Override
    @Transactional
    public Appointment save(Appointment appointment) {
        return this.repoAppointment.save(appointment);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.repoAppointment.deleteById(id);
    }
}
