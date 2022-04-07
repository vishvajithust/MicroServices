package com.clinic.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.clinic.demo.model.Employee;

public interface Employeerepository extends JpaRepository<Employee, Integer>{
	List<Employee> findByName(String name);

	Optional<Employee> findById(String id);

	void deleteById(String id);

}