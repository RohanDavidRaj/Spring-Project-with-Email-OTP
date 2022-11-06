package com.ty.springmapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springmapping.Email;
import com.ty.springmapping.EmailMessage;
import com.ty.springmapping.dto.EmployeeDTO;
import com.ty.springmapping.dto.LoginDto;
import com.ty.springmapping.dto.ResponseDTO;
import com.ty.springmapping.exception.EmployeeException;
import com.ty.springmapping.service.EmployeeServiceimplementation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
	@Autowired
	private EmployeeServiceimplementation service;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		if (loginDto.getEmpId().startsWith("TY")) {
			return new ResponseEntity<ResponseDTO>(
					new ResponseDTO(false, "OTP has send to your register Email", service.login(loginDto)),
					HttpStatus.OK);
		}
		throw new EmployeeException("Prefix your ID");

	}

	@PostMapping("/otp")
	public ResponseEntity<?> otpVerification(@RequestBody LoginDto loginDto) {
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(false, "Login Succefully", service.otp(loginDto)),
				HttpStatus.OK);

	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody EmployeeDTO entity) {
		if (entity.getEmpId().startsWith("TY")) {
			if (service.checkEmp(entity)) {
				Email.sendMail(entity.getEmpEmail(), EmailMessage.getMess(entity.getEmpName(), entity.getEmpId()));
				return new ResponseEntity<ResponseDTO>(
						new ResponseDTO(false, "Registration success", service.register(entity)), HttpStatus.OK);
			}
			// throw exception

			return new ResponseEntity<ResponseDTO>(new ResponseDTO(true, "ID is present", entity.getEmpId()),
					HttpStatus.OK);
		}
		// throw exception
		throw new EmployeeException("Prefix your ID");

	}

	@GetMapping("/education/{empId}")
	public ResponseEntity<?> education(@PathVariable String empId) {
		if (empId.startsWith("TY")) {

			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false, "Education Data ", service.education(empId)),
					HttpStatus.OK);
		}
		throw new EmployeeException("Prefix your ID");

	}

	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<?> delete(@PathVariable String empId) {
		if (empId.startsWith("TY")) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false, "Employee deleted ", service.delete(empId)),
					HttpStatus.OK);
		}
		// exception
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(true, "please  prefix your ID with TY", empId),
				HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(false, "all ", service.getAll()), HttpStatus.OK);

	}

}

//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody  ) {
//	try {
//		return new ResponseEntity<String>("login successfully", HttpStatus.OK);
//	} catch (Exception e) {
//		
//		e.printStackTrace();
//		return new ResponseEntity<String>("ID not found", HttpStatus.BAD_REQUEST);
//	}

//	@GetMapping("/education/{empId}")
//	public ResponseEntity<?> employeeEducation(@PathVariable String empId) {
//		return new ResponseEntity<EmployeeEducationDTO>(service.findByEmpId(empId), HttpStatus.OK);
//
//	}
