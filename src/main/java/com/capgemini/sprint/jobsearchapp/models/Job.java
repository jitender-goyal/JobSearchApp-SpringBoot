package com.capgemini.sprint.jobsearchapp.models;

/**
 * @author Sidharth
 * @author Sai Deepak
 * @author Yaswanth
 * @author Bondi Shashank
 * @author Ashish T
 */

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Job entity
 */
@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer jobId;
	
	@Column(name = "Title")
	@NotBlank(message = "Title must not be empty or null")
	private String title;

	@Column(name = "Location")
	@NotBlank(message = "Location must not be empty or null")
	private String location;

	@Column(name = "Description")
	@NotBlank(message = "Description must not be empty or null")
	private String description;

	@Column(name = "Salary")
	@NotNull
	private Double salary;

	@Column(name = "MinimumExperience")
	private Integer minExperience;

	@Column(name = "NoticePeriod")
	private Integer noticePeriod;
	
	@ManyToOne
	@JoinColumn(name = "empId")
	@JsonIgnore
	private Employer employer;
	
	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Application> applications = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "basket", joinColumns = @JoinColumn(name = "jobId"), inverseJoinColumns = @JoinColumn(name = "jsId"))
	@JsonIgnore
	private Set<JobSeeker> baskets = new HashSet<>();

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Job(Integer jobId, @NotBlank(message = "Title must not be empty or null") String title,
			@NotBlank(message = "Location must not be empty or null") String location,
			@NotBlank(message = "Description must not be empty or null") String description, @NotNull Double salary,
			Integer minExperience, Integer noticePeriod, Employer employer, Set<Application> applications,
			Set<JobSeeker> baskets) {
		super();
		this.jobId = jobId;
		this.title = title;
		this.location = location;
		this.description = description;
		this.salary = salary;
		this.minExperience = minExperience;
		this.noticePeriod = noticePeriod;
		this.employer = employer;
		this.applications = applications;
		this.baskets = baskets;
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

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

	public Set<JobSeeker> getBaskets() {
		return baskets;
	}

	public void setBaskets(Set<JobSeeker> baskets) {
		this.baskets = baskets;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", title=" + title + ", location=" + location + ", description=" + description
				+ ", salary=" + salary + ", minExperience=" + minExperience + ", noticePeriod=" + noticePeriod
				+ ", employer=" + employer + ", applications=" + applications + ", baskets=" + baskets + "]";
	}

}