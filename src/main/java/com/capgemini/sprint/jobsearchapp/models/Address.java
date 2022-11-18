package com.capgemini.sprint.jobsearchapp.models;

/**
 * @author Sidharth
 * @author Sai Deepak
 * @author Yaswanth
 * @author Bondi Shashank
 * @author Ashish T
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Address entity
 */
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer addId;

	@Column(name = "Area")
	@NotBlank(message = "Area must not be empty or null")
	private String area;

	@Column(name = "District")
	@NotBlank(message = "District must not be empty or null")
	private String district;

	@Column(name = "State")
	@NotBlank(message = "State must not be empty or null")
	private String state;

	@Column(name = "Pincode")
	@NotNull(message = "Pincode can not be null")
	private Integer pincode;

	public Address() {
		super();
	}

	public Address(@NotBlank(message = "Area must not be empty or null") String area,
			@NotBlank(message = "District must not be empty or null") String district,
			@NotBlank(message = "State must not be empty or null") String state,
			@NotNull(message = "Pincode can not be null") Integer pincode) {
		super();
		this.area = area;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
	}

	public Integer getAddId() {
		return addId;
	}

	public void setAddId(Integer addId) {
		this.addId = addId;
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

	@Override
	public String toString() {
		return "Address [addId=" + addId + ", area=" + area + ", district=" + district + ", state=" + state
				+ ", pincode=" + pincode + "]";
	}

}
