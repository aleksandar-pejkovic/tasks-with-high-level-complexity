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

import com.tasks_with_high_level_complexity.healthcare.model.Medication;
import com.tasks_with_high_level_complexity.healthcare.repository.MedicationRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MedicationController.class})
class MedicationControllerTest {

    @Autowired
    private MedicationController medicationController;

    @MockBean
    private MedicationRepository medicationRepository;

    @Test
    void getAllMedications() {
        Mockito.when(medicationRepository.findAll()).thenReturn(Collections.emptyList());

        medicationController.getAllMedications();

        Mockito.verify(medicationRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getMedicationById() {
        Long medicationId = 1L;
        Medication medication = new Medication();
        medication.setId(medicationId);
        Mockito.when(medicationRepository.findById(medicationId)).thenReturn(Optional.of(medication));

        medicationController.getMedicationById(medicationId);

        Mockito.verify(medicationRepository, Mockito.times(1)).findById(medicationId);
    }

    @Test
    void createMedication() {
        Medication medication = new Medication();
        medication.setId(1L);
        Mockito.when(medicationRepository.save(Mockito.any(Medication.class))).thenReturn(medication);

        medicationController.createMedication(medication);

        Mockito.verify(medicationRepository, Mockito.times(1)).save(Mockito.any(Medication.class));
    }

    @Test
    void updateMedication() {
        Long medicationId = 1L;
        Medication existingMedication = new Medication();
        existingMedication.setId(medicationId);

        Mockito.when(medicationRepository.findById(medicationId)).thenReturn(Optional.of(existingMedication));
        Mockito.when(medicationRepository.save(Mockito.any(Medication.class))).thenReturn(existingMedication);

        medicationController.updateMedication(medicationId, existingMedication);

        Mockito.verify(medicationRepository, Mockito.times(1)).findById(medicationId);
        Mockito.verify(medicationRepository, Mockito.times(1)).save(Mockito.any(Medication.class));
    }

    @Test
    void deleteMedication() {
        Long medicationId = 1L;

        medicationController.deleteMedication(medicationId);

        Mockito.verify(medicationRepository, Mockito.times(1)).deleteById(medicationId);
    }
}
