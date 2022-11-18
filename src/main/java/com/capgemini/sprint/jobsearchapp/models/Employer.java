package com.capgemini.sprint.jobsearchapp.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author siddharth
 * @author Ashish
 * @author Deepak
 * @author Shashank
 * @author Yaswanth
 *
 */
/**
 * Employeer entity
 */
@Entity
@JsonIgnoreProperties(value = { "empId" }, allowGetters = true)
public class Employer {

	/**
	 * primary id 
	 * Primary id is auto generated
	 * specifies the column name
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer empId;

	@Column(name = "OrganisationName")
	@NotBlank(message = "Name must not be empty or null")
	private String empName;

	@Column(name = "Password")
	@NotBlank(message = "password must not be empty or null")
	private String empPassword;

	@Column(name = "Email")
	@NotBlank(message = "Email must not be empty or null")
	@Email
	private String empEmail;
	
	@Column(name = "PhoneNumber")
	@Pattern(regexp = "\\d{10}",message = "Mobile number should be 10 digits")
	private String empContactNo;

	/**
	 * One employer has one address
	 */
	@OneToOne
	@Valid
	private Address address;
	/**
	 * One employer can post many jobs
	 * Ignores while printing
	 */
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "employer")
	@JsonIgnore
	private Set<Job> createdJobs = new HashSet<>();

	/**
	 * Default constructor
	 */
	public Employer() {
		super();
	}

	/**
	 * Constructor containing all the fields
	 * @param empName - Employer name
	 * @param empUserName - Employer UserName
	 * @param empPassword - Employer password
	 * @param empEmail - Employer email
	 * @param empContactNo - Employer contact no.
	 * @param address - Employer address
	 */
	public Employer(@NotBlank(message = "Name must not be empty or null") String empName,
			@NotBlank(message = "password must not be empty or null") String empPassword,
			@NotBlank(message = "Email must not be empty or null") @Email String empEmail,
			@Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits") String empContactNo,
			@Valid Address address) {
		super();
		this.empName = empName;
		this.empPassword = empPassword;
		this.empEmail = empEmail;
		this.empContactNo = empContactNo;
		this.address = address;
	}
	/**
	 * getter 
	 * @return - Employer id
	 */
	public Integer getEmpId() {
		return empId;
	}
	
	/**
	 * getter
	 * @return - Employer name
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * setter
	 * @param empName - Employer name
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * getter
	 * @return -Employer contact no
	 */
	public String getEmpContactNo() {
		return empContactNo;
	}
	/**
	 * setter
	 * @param empContactNo-Employer contact no
	 */
	public void setEmpContactNo(String empContactNo) {
		this.empContactNo = empContactNo;
	}
	/**
	 * getter
	 * @return - Address of Employer
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * setter
	 * @param address - Employer address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * getter
	 * @return - List of jobs
	 */
	public Set<Job> getCreatedJobs() {
		return createdJobs;
	}
	/**
	 * setter
	 * @param createdJobs
	 */
	public void setCreatedJobs(Set<Job> createdJobs) {
		this.createdJobs = createdJobs;
	}
	/**
	 * getter
	 * @return - Employer password
	 */
	public String getEmpPassword() {
		return empPassword;
	}
	/**
	 * setter
	 * @param empPassword - Employer password
	 */
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	/**
	 * getter
	 * @return - Employer email
	 */
	public String getEmpEmail() {
		return empEmail;
	}
	/**
	 * setter
	 * @param empEmail - Employer email
	 */
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	/**
	 * setter
	 * @param empId - Employer email
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	/**
	 * Deletes job posted by employer
	 * @param job - Job
	 * @return - true if deleted or else false
	 */
	public boolean deleteJob(Job job) {
		return this.createdJobs.remove(job);
	}
	/**
	 * Employer adds job
	 * @param job - Job
	 * @return - True if added or else false
	 */
	public boolean addJob(Job job) {
		job.setEmployer(this);
		return this.createdJobs.add(job);
	}
	/**
	 * toString method
	 * @return - All the contents of employer in string format
	 */
	@Override
	public String toString() {
		Set<Integer> createdJobsIds = new HashSet<>();
		for (Job job : this.createdJobs) {
			createdJobsIds.add(job.getJobId());
		}
		return "Employer [empId=" + empId + ", empName=" + empName + ", empEmail="
				+ empEmail + ", empContactNo=" + empContactNo + ", address=" + address 
				+ ", createdJobs=" + createdJobsIds + "]";
	}

}
