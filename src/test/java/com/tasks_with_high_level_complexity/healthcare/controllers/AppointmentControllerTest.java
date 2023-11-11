package com.tasks_with_high_level_complexity.healthcare.controllers;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tasks_with_high_level_complexity.healthcare.model.Appointment;
import com.tasks_with_high_level_complexity.healthcare.repository.AppointmentRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppointmentController.class})
class AppointmentControllerTest {

    @Autowired
    private AppointmentController appointmentController;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @Test
    void getAllAppointments() {
        Mockito.when(appointmentRepository.findAll()).thenReturn(Collections.emptyList());

        appointmentController.getAllAppointments();

        Mockito.verify(appointmentRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getAppointmentById() {
        Long appointmentId = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        Mockito.when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        appointmentController.getAppointmentById(appointmentId);

        Mockito.verify(appointmentRepository, Mockito.times(1)).findById(appointmentId);
    }

    @Test
    void createAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        Mockito.when(appointmentRepository.save(Mockito.any(Appointment.class))).thenReturn(appointment);

        appointmentController.createAppointment(appointment);

        Mockito.verify(appointmentRepository, Mockito.times(1)).save(Mockito.any(Appointment.class));
    }

    @Test
    void updateAppointment() {
        Long appointmentId = 1L;
        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(appointmentId);

        Mockito.when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(existingAppointment));
        Mockito.when(appointmentRepository.save(Mockito.any(Appointment.class))).thenReturn(existingAppointment);

        appointmentController.updateAppointment(appointmentId, existingAppointment);

        Mockito.verify(appointmentRepository, Mockito.times(1)).findById(appointmentId);
        Mockito.verify(appointmentRepository, Mockito.times(1)).save(Mockito.any(Appointment.class));
    }

    @Test
    void deleteAppointment() {
        Long appointmentId = 1L;

        appointmentController.deleteAppointment(appointmentId);

        Mockito.verify(appointmentRepository, Mockito.times(1)).deleteById(appointmentId);
    }
}
