package com.ty.springmapping.dto;

import java.util.List;

import com.ty.springmapping.entity.EmployeeEducationInfo;
import com.ty.springmapping.entity.EmployeeSecondaryInfo;
import com.ty.springmapping.entity.Skills;

import lombok.Data;

@Data
public class EmployeeDTO {

	private String empId;
	private String empName;
	private String empEmail;
	private String pass;
	private String empAge;

	private EmployeeSecondaryInfo employeeSecondaryInfo;
	private List<EmployeeEducationInfo> educationInfo;
	private List<Skills> skills;

}
