package com.capgemini.sprint.jobsearchapp.models;

/**
 * @author Sidharth
 * @author Sai Deepak
 * @author Yaswanth
 * @author Bondi Shashank
 * @author Ashish T
 */

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Application entity
 */
@Entity
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer appId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ApplicationStatus applicationStatus;
	
	@Column(name = "application_date")
	private LocalDate applDate;
	
	@ManyToOne
	private Job job;
	
	@ManyToOne
	private JobSeeker jobSeeker;

	public Application() {
		super();
	}

	public Application(Job job, JobSeeker jobSeeker) {
		super();
		this.applicationStatus = ApplicationStatus.APPLIED;
		this.job = job;
		this.jobSeeker = jobSeeker;
		this.applDate = LocalDate.now();
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Integer getAppId() {
		return appId;
	}

	public LocalDate getApplDate() {
		return applDate;
	}

	public void setApplDate(LocalDate applDate) {
		setApplDatetoToday();
	}
	
	public void setApplDatetoToday() {
		this.applDate = LocalDate.now();
	}

	@Override
	public String toString() {
		return "Application [appId=" + appId + ", applicationStatus=" + applicationStatus + ", applDate=" + applDate
				+ ", job=" + job + ", jobSeeker=" + jobSeeker + "]";
	}
	
}
