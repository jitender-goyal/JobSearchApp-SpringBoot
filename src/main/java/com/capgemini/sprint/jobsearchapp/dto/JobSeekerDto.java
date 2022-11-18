package com.capgemini.sprint.jobsearchapp.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSeekerDto {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer jsId;
	
	@NotBlank(message = "Name must not be empty or null")
	private String jsName;
	
	@Pattern(regexp = "\\d{10}",message = "Mobile number should be 10 digits")
	private String jsContactNo;

	@NotBlank(message = "Password must not be empty or null")
	private String jsPassword;

	@NotBlank(message = "Email must not be empty or null")
	@Email
	private String jsEmail;
	
	@NotBlank(message = "Area must not be empty or null")
	private String area;

	@NotBlank(message = "District must not be empty or null")
	private String district;

	@NotBlank(message = "State must not be empty or null")
	private String state;
	
	@NotNull(message = "Pincode can not be null")
	private Integer pincode;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<Integer> appliedJobsId = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<Integer> basketJobs = new ArrayList<>();

	public JobSeekerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobSeekerDto(Integer jsId, @NotBlank(message = "Name must not be empty or null") String jsName,
			@Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits") String jsContactNo,
			@NotBlank(message = "Password must not be empty or null") String jsPassword,
			@NotBlank(message = "Email must not be empty or null") @Email String jsEmail,
			@NotBlank(message = "Area must not be empty or null") String area,
			@NotBlank(message = "District must not be empty or null") String district,
			@NotBlank(message = "State must not be empty or null") String state,
			@NotNull(message = "Pincode can not be null") Integer pincode, List<Integer> appliedJobsId,
			List<Integer> basketJobs) {
		super();
		this.jsId = jsId;
		this.jsName = jsName;
		this.jsContactNo = jsContactNo;
		this.jsPassword = jsPassword;
		this.jsEmail = jsEmail;
		this.area = area;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
		this.appliedJobsId = appliedJobsId;
		this.basketJobs = basketJobs;
	}

	public Integer getJsId() {
		return jsId;
	}

	public void setJsId(Integer jsId) {
		this.jsId = jsId;
	}

	public String getJsName() {
		return jsName;
	}

	public void setJsName(String jsName) {
		this.jsName = jsName;
	}

	public @Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits") String getJsContactNo() {
		return jsContactNo;
	}

	public void setJsContactNo(String string) {
		this.jsContactNo = string;
	}

	public String getJsPassword() {
		return jsPassword;
	}

	public void setJsPassword(String jsPassword) {
		this.jsPassword = jsPassword;
	}

	public String getJsEmail() {
		return jsEmail;
	}

	public void setJsEmail(String jsEmail) {
		this.jsEmail = jsEmail;
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

	public List<Integer> getAppliedJobsId() {
		return appliedJobsId;
	}

	public void setAppliedJobsId(List<Integer> appliedJobsId) {
		this.appliedJobsId = appliedJobsId;
	}

	public List<Integer> getBasketJobs() {
		return basketJobs;
	}

	public void setBasketJobs(List<Integer> basketJobs) {
		this.basketJobs = basketJobs;
	}

	@Override
	public String toString() {
		return "JobSeekerDto [jsId=" + jsId + ", jsName=" + jsName + ", jsContactNo=" + jsContactNo 
				+ ", jsPassword=" + jsPassword + ", jsEmail=" + jsEmail + ", area=" + area + ", district="
				+ district + ", state=" + state + ", pincode=" + pincode + ", appliedJobsId=" + appliedJobsId
				+ ", basketJobs=" + basketJobs + "]";
	}

	
}
