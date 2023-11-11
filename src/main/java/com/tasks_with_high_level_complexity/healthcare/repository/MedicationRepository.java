package com.tasks_with_high_level_complexity.healthcare.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.tasks_with_high_level_complexity.healthcare.model.Medication;

@Repository
public interface MedicationRepository extends ListCrudRepository<Medication, Long> {
}
