package com.clinic.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.demo.model.Employee;
import com.clinic.demo.repository.Employeerepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
public class EmployeeController {


@Autowired
Employeerepository employeerepository;
@GetMapping("/employees")
public ResponseEntity<List<Employee>> getAllPatients(@RequestParam(required = false) String  name) {
  try {
    List<Employee> employee = new ArrayList<Employee>();

    if (name == null)
      employeerepository.findAll().forEach(employee::add);
    else
      employeerepository.findByName(name).forEach(employee::add);

    if (employee.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(employee, HttpStatus.OK);
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}



@GetMapping("/employees/{id}")
public ResponseEntity<Employee> getPatientById(@PathVariable("id") String id) {
  Optional<Employee> Data = employeerepository.findById(id);

  if (Data.isPresent()) {
    return new ResponseEntity<>(Data.get(), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}




@PostMapping("/employees")
@HystrixCommand(fallbackMethod="fallbackDisplay")
public String save() {
	System.out.println("save method ");
	throw new RuntimeException("services down");
}
	


public String fallbackDisplay() {
	System.out.println("save not working");
	return "save not working";
}
//public ResponseEntity<Employee> createPatients(@RequestBody Employee patient) {
//  try {
//	Employee _patient = employeerepository.save(new Employee(patient.getName(), patient.getDisease(),patient.getNameofconsulted_doctor()));
//    return new ResponseEntity<>(_patient, HttpStatus.CREATED);
//  } catch (Exception e) {
//    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
//}

@PutMapping("/employees/{id}")
public ResponseEntity<Employee> updateTutorial(@PathVariable("id") String id, @RequestBody Employee employee) {
  Optional<Employee> Data = employeerepository.findById(id);

  if (Data.isPresent()) {
    Employee _patient = Data.get();
    _patient.setName(employee.getName());
    _patient.setProject(employee.getProject());
    _patient.setReportingmanager(employee.getReportingmanager());
    return new ResponseEntity<>(employeerepository.save(_patient), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}







@DeleteMapping("/employees/{id}")
public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") String id) {
  try {
    employeerepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

@DeleteMapping("/employees")
public ResponseEntity<HttpStatus> deleteAllTutorials() {
  try {
    employeerepository.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}



}
