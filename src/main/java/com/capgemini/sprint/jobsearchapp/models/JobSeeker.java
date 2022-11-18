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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JobSeeker entity
 */
@Entity
public class JobSeeker {

	/** 
	 * primary id
	 * primary id auto generated
	 * specifies column name
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer jsId;
	
	@Column(name = "Name")
	@NotBlank(message = "Name must not be empty or null")
	private String jsName;

	@Column(name = "Contact")
	@NotNull
	private Long jsContactNo;

	@Column(name = "Password")
	@NotBlank(message = "Password must not be empty or null")
	private String jsPassword;

	@Column(name = "Email")
	@NotBlank(message = "Email must not be empty or null")
	@Email
	private String jsEmail;

	@OneToOne
	@Valid
	private Address address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jobSeeker")
	@JsonIgnore
	private Set<Application> appliedJobs = new HashSet<>();

	@ManyToMany(mappedBy = "baskets", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Job> basketJobs = new HashSet<>();

	public JobSeeker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobSeeker(Integer jsId, @NotBlank(message = "Name must not be empty or null") String jsName,
			@NotNull Long jsContactNo, @NotBlank(message = "UseName must not be empty or null") String jsUserName,
			@NotBlank(message = "Password must not be empty or null") String jsPassword,
			@NotBlank(message = "Email must not be empty or null") @Email String jsEmail, @Valid Address address,
			Set<Application> appliedJobs, Set<Job> basketJobs) {
		super();
		this.jsId = jsId;
		this.jsName = jsName;
		this.jsContactNo = jsContactNo;
		this.jsPassword = jsPassword;
		this.jsEmail = jsEmail;
		this.address = address;
		this.appliedJobs = appliedJobs;
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

	public Long getJsContactNo() {
		return jsContactNo;
	}

	public void setJsContactNo(Long jsContactNo) {
		this.jsContactNo = jsContactNo;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Application> getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(Set<Application> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public Set<Job> getBasketJobs() {
		return basketJobs;
	}

	public void setBasketJobs(Set<Job> basketJobs) {
		this.basketJobs = basketJobs;
	}

	@Override
	public String toString() {
		return "JobSeeker [jsId=" + jsId + ", jsName=" + jsName + ", jsContactNo=" + jsContactNo 
				+ ", jsPassword=" + jsPassword + ", jsEmail=" + jsEmail + ", address=" + address
				+ ", appliedJobs=" + appliedJobs + ", basketJobs=" + basketJobs + "]";
	}
	
	
	
}
