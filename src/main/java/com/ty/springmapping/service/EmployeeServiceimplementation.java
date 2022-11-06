package com.ty.springmapping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.springmapping.Email;
import com.ty.springmapping.dao.EmployeeDao;
import com.ty.springmapping.dto.EmployeeDTO;
import com.ty.springmapping.dto.EmployeeEducationDTO;
import com.ty.springmapping.dto.LoginDto;
import com.ty.springmapping.entity.EmployeeEducationInfo;
import com.ty.springmapping.entity.EmployeePrimaryInfo;
import com.ty.springmapping.entity.Skills;
import com.ty.springmapping.exception.EmployeeException;

@Service
public class EmployeeServiceimplementation {

	Random random = new Random(1000);
	int otp;

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private EmployeeDao dao;

//	@Autowired
//	private ModelMapper modelMapper;

	public boolean checkEmp(EmployeeDTO enDto) {

		boolean bool = true;

		EmployeePrimaryInfo findByEmpId = dao.findByEmpId(enDto.getEmpId());
		if (findByEmpId != null) {
			bool = false;
		}
		return bool;
	}

	public EmployeeDTO register(EmployeeDTO info) {
		if (dao.findByEmpId(info.getEmpId()) != null) {
			throw new EmployeeException("ID is present");
		} else {
			EmployeePrimaryInfo saved = modelMapper.map(info, EmployeePrimaryInfo.class);

			saved.getEmployeeSecondaryInfo().setEmployeePrimaryInfo(saved);

			for (EmployeeEducationInfo i : saved.getEducationInfo()) {
				i.setEmployeePrimaryInfo(saved);
			}
			List<EmployeePrimaryInfo> list = new ArrayList<>();
			list.add(saved);

			List<Skills> listSkill = new ArrayList<>();
			listSkill.addAll(saved.getSkills());

			for (Skills i : saved.getSkills()) {
				i.setEmployeePrimaryInfo(list);

				for (EmployeePrimaryInfo j : i.getEmployeePrimaryInfo()) {
					j.setSkills(listSkill);
				}

			}

			EmployeePrimaryInfo ss = dao.save(saved);
			return modelMapper.map(ss, EmployeeDTO.class);
		}

	}

	public EmployeeEducationDTO education(String empId) {
		EmployeePrimaryInfo info = dao.findByEmpId(empId);

		List<EmployeeEducationInfo> educationInfo = new ArrayList<>();

		EmployeeEducationDTO dto2 = modelMapper.map(info, EmployeeEducationDTO.class);

		dto2.setEmpId(info.getEmpId());
		dto2.setEmpName(info.getEmpName());

		educationInfo.addAll(info.getEducationInfo());

		return dto2;
	}

	public EmployeeDTO delete(String empId) {
		if (dao.findByEmpId(empId) != null) {
			EmployeePrimaryInfo primaryInfo = dao.findByEmpId(empId);
			dao.delete(primaryInfo);
			return modelMapper.map(primaryInfo, EmployeeDTO.class);
		}

		throw new EmployeeException("ID is not present");

	}

	public List<EmployeeDTO> getAll() {
		List<EmployeePrimaryInfo> findAll = (List<EmployeePrimaryInfo>) dao.findAll();

		List<EmployeeDTO> dto = new ArrayList<>();

		for (EmployeePrimaryInfo e : findAll) {
//			EmployeeDTO save = new EmployeeDTO(e.getEmpId(), e.getEmpName(), e.getEmpAge(), e.getEmployeeSecondaryInfo(),
//					e.getEducationInfo(),e.getSkills());
			EmployeeDTO dto2 = modelMapper.map(e, EmployeeDTO.class);
			dto.add(dto2);
		}

		return dto;
	}

	public LoginDto login(LoginDto loginDto) {
		EmployeePrimaryInfo findByEmpId = dao.findByEmpId(loginDto.getEmpId());

		if (findByEmpId != null) {

			if (findByEmpId.getPass().equals(loginDto.getPass())) {
				otp = random.nextInt(999999);
				Email.sendMail(findByEmpId.getEmpEmail(), "OTP for verification " + otp);

				LoginDto map = modelMapper.map(findByEmpId, LoginDto.class);
				map.setOtp(otp);
				//System.out.println("@@@@@@@@@@@@@@@@@@@@@"+map.getOtp());
				return map;

			}
			Email.sendMail(findByEmpId.getEmpEmail(),
					"Someone is trying to access your account \n please reset your password ");
			throw new EmployeeException("Password is Incorrect");

		}
		throw new EmployeeException("ID is not present");
	}

	public LoginDto otp(LoginDto loginDto) {
		if (loginDto.getOtp() == otp) {
			//EmployeePrimaryInfo findByEmpId = dao.findByEmpId(loginDto.getEmpId());
			//EmployeeDTO map = modelMapper.map(findByEmpId, EmployeeDTO.class);
			return loginDto;
		}
		throw new EmployeeException("OTP is Incorrect");
	}

//	public EmployeeEducationDTO findByEmpId(String empId) {
//		
//		EmployeePrimaryInfo primaryInfo=dao.findByEmpId(empId);
//		
//		EmployeeEducationDTO dto=EntityToDto(primaryInfo);
//		dto.setEmpId(empId);
//		dto.setEmpName(primaryInfo.getEmpName());
//		dto.setEducation(primaryInfo.getEducationInfo());
//		return dto;
//
//	}

}
