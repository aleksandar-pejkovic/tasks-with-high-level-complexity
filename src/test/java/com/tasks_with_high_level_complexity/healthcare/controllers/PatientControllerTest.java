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

import com.tasks_with_high_level_complexity.healthcare.model.Patient;
import com.tasks_with_high_level_complexity.healthcare.repository.PatientRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PatientController.class})
class PatientControllerTest {

    @Autowired
    private PatientController patientController;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    void getAllPatients() {
        Mockito.when(patientRepository.findAll()).thenReturn(Collections.emptyList());

        patientController.getAllPatients();

        Mockito.verify(patientRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getPatientById() {
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));

        patientController.getPatientById(patientId);

        Mockito.verify(patientRepository, Mockito.times(1)).findById(patientId);
    }

    @Test
    void createPatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);

        patientController.createPatient(patient);

        Mockito.verify(patientRepository, Mockito.times(1)).save(Mockito.any(Patient.class));
    }

    @Test
    void updatePatient() {
        Long patientId = 1L;
        Patient existingPatient = new Patient();
        existingPatient.setId(patientId);

        Mockito.when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(existingPatient);

        patientController.updatePatient(patientId, existingPatient);

        Mockito.verify(patientRepository, Mockito.times(1)).findById(patientId);
        Mockito.verify(patientRepository, Mockito.times(1)).save(Mockito.any(Patient.class));
    }

    @Test
    void deletePatient() {
        Long patientId = 1L;

        patientController.deletePatient(patientId);

        Mockito.verify(patientRepository, Mockito.times(1)).deleteById(patientId);
    }
}
