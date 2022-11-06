package com.ty.springmapping.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ty.springmapping.dto.ResponseDTO;
import com.ty.springmapping.exception.EmployeeException;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(EmployeeException.class)

	public ResponseEntity<?> exceptionHandler(HttpServletRequest request, EmployeeException exception) {
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(true, exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}
}
