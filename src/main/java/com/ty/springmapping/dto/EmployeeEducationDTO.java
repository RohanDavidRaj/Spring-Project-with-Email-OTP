package com.ty.springmapping.dto;

import java.util.List;

import com.ty.springmapping.entity.EmployeeEducationInfo;
import com.ty.springmapping.entity.Skills;

import lombok.Data;

@Data
public class EmployeeEducationDTO {
	
	private String empId;
	private String empName;
	private List<EmployeeEducationInfo> education;
	

}
