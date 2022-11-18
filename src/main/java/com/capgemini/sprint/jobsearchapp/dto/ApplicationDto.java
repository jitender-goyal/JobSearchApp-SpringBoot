package com.capgemini.sprint.jobsearchapp.dto;

import java.time.LocalDate;

public class ApplicationDto {

	private Integer appId;
	
	private String applicationStatus;
	
	private LocalDate applDate;
	
	private Integer jobId;
	
	private Integer jobSeekerId;

	public ApplicationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationDto(Integer appId, String applicationStatus, LocalDate applDate, Integer jobId,
			Integer jobSeekerId) {
		super();
		this.appId = appId;
		this.applicationStatus = applicationStatus;
		this.applDate = applDate;
		this.jobId = jobId;
		this.jobSeekerId = jobSeekerId;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public LocalDate getApplDate() {
		return applDate;
	}

	public void setApplDate(LocalDate applDate) {
		this.applDate = applDate;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(Integer jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	@Override
	public String toString() {
		return "ApplicationDto [appId=" + appId + ", applicationStatus=" + applicationStatus + ", applDate=" + applDate
				+ ", jobId=" + jobId + ", jobSeekerId=" + jobSeekerId + "]";
	}
	
}
