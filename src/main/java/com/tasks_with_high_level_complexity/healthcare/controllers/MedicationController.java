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

import com.tasks_with_high_level_complexity.healthcare.model.Medication;
import com.tasks_with_high_level_complexity.healthcare.repository.MedicationRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationController(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Medication getMedicationById(@PathVariable Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medication not found with id: " + id));
    }

    @PostMapping
    public Medication createMedication(@RequestBody Medication medication) {
        return medicationRepository.save(medication);
    }

    @PutMapping("/{id}")
    public Medication updateMedication(@PathVariable Long id, @RequestBody Medication updatedMedication) {
        return medicationRepository.findById(id)
                .map(medication -> {
                    medication.setMedicationName(updatedMedication.getMedicationName());
                    // Update other fields as needed
                    return medicationRepository.save(medication);
                })
                .orElseThrow(() -> new EntityNotFoundException("Medication not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationRepository.deleteById(id);
    }
}
