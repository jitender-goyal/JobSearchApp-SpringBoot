package com.capgemini.sprint.jobsearchapp.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployerDto {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer empId;

	@NotBlank(message = "Name must not be empty or null")
	private String empName;
	/**
	 * Gets ignored while printing
	 */
	@NotBlank(message = "password must not be empty or null")
	private String empPassword;

	@NotBlank(message = "Email must not be empty or null")
	@Email
	private String empEmail;
	
	@Pattern(regexp = "\\d{10}",message = "Mobile number should be 10 digits")
	private String empContactNo;

	@NotBlank(message = "Area must not be empty or null")
	private String area;

	@NotBlank(message = "District must not be empty or null")
	private String district;

	@NotBlank(message = "State must not be empty or null")
	private String state;
	
	@NotNull(message = "Pincode can not be null")
	private Integer pincode;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Set<Integer> createdJobs = new HashSet<>();

	public EmployerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployerDto(Integer empId, @NotBlank(message = "Name must not be empty or null") String empName,
			@NotBlank(message = "password must not be empty or null") String empPassword,
			@NotBlank(message = "Email must not be empty or null") @Email String empEmail,
			@Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits") String empContactNo,
			@NotBlank(message = "Area must not be empty or null") String area,
			@NotBlank(message = "District must not be empty or null") String district,
			@NotBlank(message = "State must not be empty or null") String state,
			@NotNull(message = "Pincode can not be null") Integer pincode, Set<Integer> createdJobs) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empPassword = empPassword;
		this.empEmail = empEmail;
		this.empContactNo = empContactNo;
		this.area = area;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
		this.createdJobs = createdJobs;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpContactNo() {
		return empContactNo;
	}

	public void setEmpContactNo(String empContactNo) {
		this.empContactNo = empContactNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Set<Integer> getCreatedJobs() {
		return createdJobs;
	}

	public void setCreatedJobs(Set<Integer> createdJobs) {
		this.createdJobs = createdJobs;
	}

	@Override
	public String toString() {
		return "EmployerDto [empId=" + empId + ", empName=" + empName
				+ ", empPassword=" + empPassword + ", empEmail=" + empEmail + ", empContactNo=" + empContactNo
				+ ", area=" + area + ", district=" + district + ", state=" + state + ", pincode=" + pincode
				+ ", createdJobs=" + createdJobs + "]";
	}

}
