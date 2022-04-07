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

import com.clinic.demo.model.Patient;
import com.clinic.demo.repository.Patientrepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
public class PatientController {


@Autowired
Patientrepository patientrepository;
@GetMapping("/patients")
public ResponseEntity<List<Patient>> getAllPatients(@RequestParam(required = false) String  name) {
  try {
    List<Patient> patient = new ArrayList<Patient>();

    if (name == null)
      patientrepository.findAll().forEach(patient::add);
    else
      patientrepository.findByName(name).forEach(patient::add);

    if (patient.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(patient, HttpStatus.OK);
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}



@GetMapping("/patient/{id}")
public ResponseEntity<Patient> getPatientById(@PathVariable("id") String id) {
  Optional<Patient> Data = patientrepository.findById(id);

  if (Data.isPresent()) {
    return new ResponseEntity<>(Data.get(), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}




@PostMapping("/patients")
@HystrixCommand(fallbackMethod="fallbackDisplay")
public String save() {
	System.out.println("save method ");
	throw new RuntimeException("services down");
}
	


public String fallbackDisplay() {
	System.out.println("save not working");
	return "save not working";
}
//public ResponseEntity<Patient> createPatients(@RequestBody Patient patient) {
//  try {
//	Patient _patient = patientrepository.save(new Patient(patient.getName(), patient.getDisease(),patient.getNameofconsulted_doctor()));
//    return new ResponseEntity<>(_patient, HttpStatus.CREATED);
//  } catch (Exception e) {
//    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
//}

@PutMapping("/patients/{id}")
public ResponseEntity<Patient> updateTutorial(@PathVariable("id") String id, @RequestBody Patient patient) {
  Optional<Patient> Data = patientrepository.findById(id);

  if (Data.isPresent()) {
    Patient _patient = Data.get();
    _patient.setName(patient.getName());
    _patient.setDisease(patient.getDisease());
    _patient.setNameofconsulted_doctor(patient.getNameofconsulted_doctor());
    return new ResponseEntity<>(patientrepository.save(_patient), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}







@DeleteMapping("/patients/{id}")
public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") String id) {
  try {
    patientrepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

@DeleteMapping("/patients")
public ResponseEntity<HttpStatus> deleteAllTutorials() {
  try {
    patientrepository.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}



}
