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

import com.capgemini.sprint.jobsearchapp.dto.ApplicationDto;
import com.capgemini.sprint.jobsearchapp.dto.JobDto;
import com.capgemini.sprint.jobsearchapp.dto.JobSeekerDto;

/**
 * interface JobSeekerService
 * Provides all methods for implementation
 */
public interface JobSeekerService {

	/**
	 * This method takes, jobseeker object as the argument and creates the Jobseeker in the database
	 * @param jobSeeker
	 * @return returns the JobSeeker object.
	 */
	JobSeekerDto createJobSeeker(JobSeekerDto jobSeekerDto);
	
	/**
	 * This method, takes the Id and returns the jobseeker
	 * @param jsId
	 * @return JobSeeker
	 * @throws UnavailableException
	 */
	JobSeekerDto display(Integer jsId) throws UnavailableException;
	
	/**
	 * This method returns the HashMap of all Jobseekers
	 * @return HashMap of all JobSeekers
	 */
	ArrayList<JobSeekerDto> displayAllJobSeeker();
	
	/**
	 * This method takes jsId and deletes the Jobseeker in the database
	 * @param jsId
	 */
	void deleteJobSeeker(Integer jsId) throws UnavailableException;
	
	/**
	 * This method returns the HashMap of all jobs created, from the database.
	 * @return returns the HashMap of all the jobs.
	 */
	ArrayList<JobDto> displayAllJobs();
	
	/**
	 * This method returns job with jobId
	 * @param jobId
	 * @return returns job with jobId
	 */
	JobDto displayJob(Integer jobId) throws UnavailableException;
	
	/**
	 * This method provides the functionality to apply to the job in the database. creates an application.
	 * @param jsId
	 * @param jobId
	 */
	String applyToJob(Integer jsId, Integer jobId) throws UnavailableException;
	
	/**
	 * This method provides the functionality to withdraw an application.
	 * @param jsId
	 * @param jobId
	 */
	Boolean withdrawJob(Integer jsId, Integer jobId) throws UnavailableException;
	
	/**
	 * This method takes the jsId as argument and returns the Map object of all the AppliedJobs from the database. 
	 * @param jsId
	 * @return returns the HashMap of all AppliedJobs 
	 */
	ArrayList<JobDto> displayAppliedJobs(Integer jsId);
	
	/**
	 * This method takes the jsId and jobId as the arguments and adds the job into the basket in the database.
	 * @param jsId
	 * @param jobId
	 * @return returns the Job object.
	 */
	JobDto addJobToBasket(Integer jsId, Integer jobId) throws UnavailableException;
	
	/**
	 * This method takes arguments as jsID and jobId, and if it finds the correct values corresponding to them, it returns Job
	 * @param jsId
	 * @return if any values are there, it will return respective Job
	 */
	JobDto deleteJobFromBasket(Integer jsId, Integer jobId) throws UnavailableException;
	
	/**
	 * This method takes location as the argument and returns the list of all the jobs according to the location from the database
	 * @param location
	 * @return returns the list of all jobs according to location
	 */
	
	ArrayList<JobDto> applyAllBasketJobs(Integer jsId) throws UnavailableException;
	
	
	ArrayList<JobDto> getJobByLocation(String location);
	
	/**
	 * @throws UnavailableException 
	 * @param jsId
	 * @param jobId
	 */
	String getApplStatus(Integer jsId, Integer jobId) throws UnavailableException;
	
	ApplicationDto getApplication(Integer jsId, Integer jobId) throws UnavailableException;

	JobSeekerDto updateJobSeeker(Integer jsId, JobSeekerDto jobSeekerDto) throws UnavailableException;
	
	ArrayList<JobDto> displayBasketJobs(Integer jsId) throws UnavailableException;
}
