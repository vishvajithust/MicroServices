package com.clinic.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.clinic.demo.model.Patient;

public interface Patientrepository extends JpaRepository<Patient, Integer>{
	List<Patient> findByName(String name);

	Optional<Patient> findById(String id);

	void deleteById(String id);

}