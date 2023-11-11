package com.tasks_with_high_level_complexity.healthcare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks_with_high_level_complexity.healthcare.model.Appointment;
import com.tasks_with_high_level_complexity.healthcare.repository.AppointmentRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id: " + id));
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setDoctor(updatedAppointment.getDoctor());
                    appointment.setPatient(updatedAppointment.getPatient());
                    appointment.setAppointmentDateTime(updatedAppointment.getAppointmentDateTime());
                    return appointmentRepository.save(appointment);
                })
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
    }
}
