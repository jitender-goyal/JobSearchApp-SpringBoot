package com.capgemini.sprint.jobsearchapp.service;

import java.util.ArrayList;

/**
 * @author Sidharth
 * @author Sai Deepak
 * @author Yaswanth
 * @author Bondi Shashank
 * @author Ashish T
 */

import javax.servlet.UnavailableException;

import com.capgemini.sprint.jobsearchapp.dto.EmployerDto;
import com.capgemini.sprint.jobsearchapp.dto.JobDto;
import com.capgemini.sprint.jobsearchapp.dto.JobSeekerDto;
import com.capgemini.sprint.jobsearchapp.models.Job;

public interface EmployerService {

	void deleteEmployer(Integer empId) throws UnavailableException;
	ArrayList<EmployerDto> displayAllEmployers();
	EmployerDto displayEmployer(Integer empId) throws UnavailableException;
	JobDto addJob(Integer empId, JobDto jobDto) throws UnavailableException;
	boolean deleteJob(Integer empId, Integer jobId);
	JobDto searchJob(Integer jobId) throws UnavailableException;
	JobDto editAJob(Integer empId, Integer jobId, Job editedJob) throws UnavailableException;
	ArrayList<JobDto> displayCreatedJobs(Integer empId) throws UnavailableException;
	EmployerDto updateEmployer(EmployerDto employerDto, Integer empId) throws UnavailableException;
	EmployerDto createEmployer(EmployerDto employerDto);
	ArrayList<JobSeekerDto> searchJobSeekerByJobId(Integer jobId) throws UnavailableException;
	
	/**
	 * @throws UnavailableException 
	 * @param jsId
	 * @param jobId
	 * @param ord - Ordinal value of ApplicationStatus Enum
	 */
	String changeApplStatus(Integer jsId, Integer jobId, Integer ord) throws UnavailableException;
}
