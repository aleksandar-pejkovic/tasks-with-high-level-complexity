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

import com.tasks_with_high_level_complexity.healthcare.model.Doctor;
import com.tasks_with_high_level_complexity.healthcare.repository.DoctorRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DoctorController.class})
class DoctorControllerTest {

    @Autowired
    private DoctorController doctorController;

    @MockBean
    private DoctorRepository doctorRepository;

    @Test
    void getAllDoctors() {
        Mockito.when(doctorRepository.findAll()).thenReturn(Collections.emptyList());

        doctorController.getAllDoctors();

        Mockito.verify(doctorRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getDoctorById() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

        doctorController.getDoctorById(doctorId);

        Mockito.verify(doctorRepository, Mockito.times(1)).findById(doctorId);
    }

    @Test
    void createDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        Mockito.when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);

        doctorController.createDoctor(doctor);

        Mockito.verify(doctorRepository, Mockito.times(1)).save(Mockito.any(Doctor.class));
    }

    @Test
    void updateDoctor() {
        Long doctorId = 1L;
        Doctor existingDoctor = new Doctor();
        existingDoctor.setId(doctorId);

        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(existingDoctor));
        Mockito.when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(existingDoctor);

        doctorController.updateDoctor(doctorId, existingDoctor);

        Mockito.verify(doctorRepository, Mockito.times(1)).findById(doctorId);
        Mockito.verify(doctorRepository, Mockito.times(1)).save(Mockito.any(Doctor.class));
    }

    @Test
    void deleteDoctor() {
        Long doctorId = 1L;

        doctorController.deleteDoctor(doctorId);

        Mockito.verify(doctorRepository, Mockito.times(1)).deleteById(doctorId);
    }
}
