package com.capgemini.sprint.jobsearchapp.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobDto {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer jobId;

	private String title;

	private String location;

	private String description;

	private Double salary;

	private Integer minExperience;

	private Integer noticePeriod;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer employerId;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Set<Integer> applications = new HashSet<>();

	public JobDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobDto(Integer jobId, String title, String location, String description, Double salary,
			Integer minExperience, Integer noticePeriod, Integer employerId,
			Set<Integer> applications) {
		super();
		this.jobId = jobId;
		this.title = title;
		this.location = location;
		this.description = description;
		this.salary = salary;
		this.minExperience = minExperience;
		this.noticePeriod = noticePeriod;
		this.employerId = employerId;
		this.applications = applications;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(Integer minExperience) {
		this.minExperience = minExperience;
	}

	public Integer getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(Integer noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public Integer getEmployerId() {
		return employerId;
	}

	public void setEmployerId(Integer employerId) {
		this.employerId = employerId;
	}

	public Set<Integer> getApplications() {
		return applications;
	}

	public void setApplications(Set<Integer> applications) {
		this.applications = applications;
	}

	@Override
	public String toString() {
		return "JobDto [jobId=" + jobId + ", title=" + title + ", location=" + location + ", description=" + description
				+ ", salary=" + salary + ", minExperience=" + minExperience + ", noticePeriod=" + noticePeriod
				+ ", employerId=" + employerId + ", applications=" + applications + "]";
	}

}
