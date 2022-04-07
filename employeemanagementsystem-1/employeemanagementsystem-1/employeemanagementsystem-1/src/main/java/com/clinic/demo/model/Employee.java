package com.clinic.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Employee {
	@Id
	private String id;
	private String name;
	private String project;
	private String reportingmanager;
	
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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getReportingmanager() {
		return reportingmanager;
	}

	public void setReportingmanager(String reportingmanager) {
		this.reportingmanager = reportingmanager;
	}

	public Employee() {
		
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", project=" + project + ", reportingmanager="
				+ reportingmanager + "]";
	}

	public Employee( String name, String project, String reportingmanager) {
		super();
		
		this.name = name;
		this.project = project;
		this.reportingmanager = reportingmanager;
	}
	
	
	
}
