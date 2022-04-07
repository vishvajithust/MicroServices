package com.clinic.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Patient {
	@Id
	private String id;
	private String name;
	private String disease;
	private String nameofconsulted_doctor;
	
	public Patient() {
		
	}
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", disease=" + disease + ", nameofconsulted_doctor="
				+ nameofconsulted_doctor + "]";
	}

	public Patient( String name, String disease, String nameofconsulted_doctor) {
		super();
		
		this.name = name;
		this.disease = disease;
		this.nameofconsulted_doctor = nameofconsulted_doctor;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getNameofconsulted_doctor() {
		return nameofconsulted_doctor;
	}
	public void setNameofconsulted_doctor(String nameofconsulted_doctor) {
		this.nameofconsulted_doctor = nameofconsulted_doctor;
	}
	
}
