package com.ty.springmapping.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@Table(name = "employee_primary_info")
public class EmployeePrimaryInfo {
	@Id
	private String empId;
	private String empName;
	private String empEmail;
	private String pass;
	private String empAge;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employeePrimaryInfo")
	@JsonManagedReference
	private EmployeeSecondaryInfo employeeSecondaryInfo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeePrimaryInfo")
	@JsonManagedReference
	private List<EmployeeEducationInfo> educationInfo;
	
//	@ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
//	@JoinTable(name = "emp_skills",
//	           joinColumns = {@JoinColumn(name="emp_Id",referencedColumnName = "empId")},
//	           inverseJoinColumns = {@JoinColumn(name="skill_id",referencedColumnName = "skillId")
//	        		   
//	           }
//	)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Employee_techskills",
	joinColumns = {@JoinColumn(name="emp_id")},
	inverseJoinColumns = {@JoinColumn(name="skid")})
	private List<Skills> skills;
	
	
//	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinTable(name = "Employee_techskills",
//	joinColumns = {@JoinColumn(name="emp_id",referencedColumnName = "empId")},
//	inverseJoinColumns = {@JoinColumn(name="skid",referencedColumnName = "skillId")})
//	private List<Skills> skills;
	
}
