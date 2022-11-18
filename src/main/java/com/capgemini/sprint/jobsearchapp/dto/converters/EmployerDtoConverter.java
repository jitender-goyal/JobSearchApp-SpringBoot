package com.capgemini.sprint.jobsearchapp.dto.converters;

import com.capgemini.sprint.jobsearchapp.dto.EmployerDto;
import com.capgemini.sprint.jobsearchapp.models.Address;
import com.capgemini.sprint.jobsearchapp.models.Employer;
import com.capgemini.sprint.jobsearchapp.models.Job;

public class EmployerDtoConverter {

	public Employer dtoToEmployer(EmployerDto employerDto) {
		Employer employer = new Employer();
		Address address = new Address();
		address.setArea(employerDto.getArea());
		address.setDistrict(employerDto.getDistrict());
		address.setState(employerDto.getState());
		address.setPincode(employerDto.getPincode());
		employer.setAddress(address);
		employer.setEmpName(employerDto.getEmpName());
		employer.setEmpPassword(employerDto.getEmpPassword());
		employer.setEmpContactNo(employerDto.getEmpContactNo());
		employer.setEmpEmail(employerDto.getEmpEmail());
		return employer;
	}
	
	public EmployerDto employerToDto(Employer employer) {
		EmployerDto employerDto = new EmployerDto();
		employerDto.setArea(employer.getAddress().getArea());
		employerDto.setDistrict(employer.getAddress().getDistrict());
		employerDto.setState(employer.getAddress().getState());
		employerDto.setPincode(employer.getAddress().getPincode());
		employerDto.setEmpId(employer.getEmpId());
		employerDto.setEmpName(employer.getEmpName());
		employerDto.setEmpPassword(employer.getEmpPassword());
		employerDto.setEmpContactNo(employer.getEmpContactNo());
		employerDto.setEmpEmail(employer.getEmpEmail());
		for (Job job : employer.getCreatedJobs()) {
			employerDto.getCreatedJobs().add(job.getJobId());
		}
		return employerDto;
	}
}
