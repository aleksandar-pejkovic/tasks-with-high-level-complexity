package com.tasks_with_high_level_complexity.healthcare.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.tasks_with_high_level_complexity.healthcare.model.Patient;

@Repository
public interface PatientRepository extends ListCrudRepository<Patient, Long> {
}
