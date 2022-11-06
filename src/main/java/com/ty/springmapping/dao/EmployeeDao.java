package com.ty.springmapping.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springmapping.entity.EmployeePrimaryInfo;

public interface EmployeeDao extends JpaRepository<EmployeePrimaryInfo, String> {
	
	public EmployeePrimaryInfo findByEmpId(String empId);

}
