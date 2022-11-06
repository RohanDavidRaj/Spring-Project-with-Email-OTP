package com.ty.springmapping.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
@Table(name = "employee_education_info")
public class EmployeeEducationInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eduId;
	private String education;


	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private EmployeePrimaryInfo employeePrimaryInfo;

}
