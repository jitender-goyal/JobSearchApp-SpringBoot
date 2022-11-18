package com.capgemini.sprint.jobsearchapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.UnavailableException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sprint.jobsearchapp.dto.ApplicationDto;
import com.capgemini.sprint.jobsearchapp.dto.JobDto;
import com.capgemini.sprint.jobsearchapp.dto.JobSeekerDto;
import com.capgemini.sprint.jobsearchapp.service.JobSeekerService;

@RestController
@RequestMapping("/jobseekers")
@CrossOrigin("*")
public class JobSeekerController {

	@Autowired
	private JobSeekerService jobSeekerService;
	
	@PostMapping
	public ResponseEntity<JobSeekerDto> createJobSeeker(@RequestBody @Valid JobSeekerDto jobSeekerDto) {
		JobSeekerDto jobSeekerDtoReturn = jobSeekerService.createJobSeeker(jobSeekerDto);
		ResponseEntity<JobSeekerDto> response = new ResponseEntity<JobSeekerDto>(jobSeekerDtoReturn, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping
	public ResponseEntity<ArrayList<JobSeekerDto>> displayAllJobSeeker() {
		ArrayList<JobSeekerDto> jobSeekerDtos = jobSeekerService.displayAllJobSeeker();
		ResponseEntity<ArrayList<JobSeekerDto>> response = new ResponseEntity<ArrayList<JobSeekerDto>>(jobSeekerDtos, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/{jsId}")
	public ResponseEntity<JobSeekerDto> displayJobSeeker(@PathVariable Integer jsId) throws UnavailableException {
		JobSeekerDto jobSeekerDtoReturn = jobSeekerService.display(jsId);
		ResponseEntity<JobSeekerDto> response = new ResponseEntity<JobSeekerDto>(jobSeekerDtoReturn, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/{jsId}")
	public ResponseEntity<Integer> deleteJobSeeker(@PathVariable Integer jsId) throws UnavailableException{
		jobSeekerService.deleteJobSeeker(jsId);
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(jsId, HttpStatus.NO_CONTENT);
		return response;
	}
	
	@PutMapping("/{jsId}")
	public ResponseEntity<JobSeekerDto> updateJobSeeker(@PathVariable Integer jsId, @RequestBody @Valid JobSeekerDto jobSeekerDto) throws UnavailableException{
		JobSeekerDto jobSeekerDtoReturn = jobSeekerService.updateJobSeeker(jsId, jobSeekerDto);
		ResponseEntity<JobSeekerDto> response = new ResponseEntity<JobSeekerDto>(jobSeekerDtoReturn, HttpStatus.CREATED);
		return response;		
	}
	
	@GetMapping("/jobs")
	public ResponseEntity<List<JobDto>> displayAllJobs() {
		List<JobDto> jobDtos = jobSeekerService.displayAllJobs();
		ResponseEntity<List<JobDto>> response = new ResponseEntity<List<JobDto>>(jobDtos, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/jobs/{jobId}")
	public ResponseEntity<JobDto> searchJob(@PathVariable Integer jobId) throws UnavailableException {
		JobDto jobDto = jobSeekerService.displayJob(jobId);
		ResponseEntity<JobDto> response = new ResponseEntity<JobDto>(jobDto, HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{jsId}/applytojobs/{jobId}")
	public ResponseEntity<String> applyJob(@PathVariable Integer jsId, @PathVariable Integer jobId) throws UnavailableException{
		String response = jobSeekerService.applyToJob(jsId, jobId);
		if(response.compareTo("SUCCESS")==0) {
			return new ResponseEntity<String>("Successfully Applied",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>(response,HttpStatus.ALREADY_REPORTED);
		}
	}
	
	@DeleteMapping("/{jsId}/withdrawfromjobs/{jobId}")
	public ResponseEntity<String> withdrawJob(@PathVariable Integer jsId, @PathVariable Integer jobId) throws UnavailableException{
		if(jobSeekerService.withdrawJob(jsId, jobId)) {
			return new ResponseEntity<String>("Successfully Withdrawn",HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{jsId}/appliedjobs")
	public ResponseEntity<List<JobDto>> displayAppliedJobs(@PathVariable Integer jsId){
		List<JobDto> jobDtos = jobSeekerService.displayAppliedJobs(jsId);
		ResponseEntity<List<JobDto>> response = new ResponseEntity<List<JobDto>>(jobDtos, HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{jsId}/basketjobs/{jobId}")
	public ResponseEntity<JobDto> basketAddJob(@PathVariable Integer jsId, @PathVariable Integer jobId) throws UnavailableException {
		JobDto jobDto = jobSeekerService.addJobToBasket(jsId,jobId);
		ResponseEntity<JobDto> response = new ResponseEntity<JobDto>(jobDto, HttpStatus.CREATED);
		return response;
	}
	
	@DeleteMapping("/{jsId}/deletebasketjobs/{jobId}")
	public ResponseEntity<JobDto> basketDeleteJob(@PathVariable Integer jsId, @PathVariable Integer jobId) throws UnavailableException {
		JobDto jobDto = jobSeekerService.deleteJobFromBasket(jsId, jobId);
		ResponseEntity<JobDto> response = new ResponseEntity<JobDto>(jobDto, HttpStatus.NO_CONTENT);
		return response;
	}
	
	
//	@GetMapping("jobbylocation/{location}")
//	public ResponseEntity<List<JobDto>> getJobByLocation(@PathVariable String location){
//		List<JobDto> jobDtos = jobSeekerService.getJobByLocation(location);
//		ResponseEntity<List<JobDto>> response = new ResponseEntity<List<JobDto>>(jobDtos, HttpStatus.OK);
//		return response;
//	}
	
	@GetMapping("/{jsId}/jobapplication/{jobId}")
	public ResponseEntity<ApplicationDto> getApplication(@PathVariable Integer jsId, @PathVariable Integer jobId) throws UnavailableException {
		ApplicationDto applicationDto = jobSeekerService.getApplication(jsId, jobId);
		ResponseEntity<ApplicationDto> response = new ResponseEntity<ApplicationDto>(applicationDto, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/{jsId}/jobapplstatus/{jobId}")
	public ResponseEntity<String> getApplicationStatus(@PathVariable Integer jsId, @PathVariable Integer jobId) throws UnavailableException {
		String response = jobSeekerService.getApplStatus(jsId, jobId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{jsId}/basketjobs")
	public ResponseEntity<ArrayList<JobDto>> getBasketJobs(@PathVariable Integer jsId) throws UnavailableException {
		ArrayList<JobDto> jobs = jobSeekerService.displayBasketJobs(jsId);
		return new ResponseEntity<ArrayList<JobDto>>(jobs, HttpStatus.OK);
	}
}
