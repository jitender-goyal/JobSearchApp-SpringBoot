package com.capgemini.sprint.jobsearchapp.controllers;

import java.util.ArrayList;
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

import com.capgemini.sprint.jobsearchapp.dto.EmployerDto;
import com.capgemini.sprint.jobsearchapp.dto.JobDto;
import com.capgemini.sprint.jobsearchapp.dto.JobSeekerDto;
import com.capgemini.sprint.jobsearchapp.models.Job;
import com.capgemini.sprint.jobsearchapp.service.EmployerService;

@RestController
@RequestMapping("/employers")
@CrossOrigin("*")
public class EmployerController {

	@Autowired
	private EmployerService employerService;
	
	@PostMapping
	public ResponseEntity<EmployerDto> setEmployer(@RequestBody @Valid EmployerDto employerDto) {
		EmployerDto employerDtoReturn = employerService.createEmployer(employerDto);
		ResponseEntity<EmployerDto> response = new ResponseEntity<EmployerDto>(employerDtoReturn, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping
	public ResponseEntity<ArrayList<EmployerDto>> displayAllEmployers(){
		ArrayList<EmployerDto> employerDtos = employerService.displayAllEmployers();
		ResponseEntity<ArrayList<EmployerDto>> response = new ResponseEntity<ArrayList<EmployerDto>>(employerDtos,HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/{empId}")
	public ResponseEntity<Integer> deleteEmployer(@PathVariable Integer empId) throws UnavailableException{
		employerService.deleteEmployer(empId);
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(empId, HttpStatus.NO_CONTENT);
		return response;
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<EmployerDto> getEmployer(@PathVariable Integer empId) throws UnavailableException{
		EmployerDto employerDtoReturn = employerService.displayEmployer(empId);
		ResponseEntity<EmployerDto> response = new ResponseEntity<EmployerDto>(employerDtoReturn, HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/{empId}/jobs")
	public ResponseEntity<JobDto> createJob(@PathVariable Integer empId, @RequestBody @Valid JobDto jobDto) throws UnavailableException {
		JobDto jobDtoReturn = employerService.addJob(empId, jobDto);
		ResponseEntity<JobDto> response = new ResponseEntity<JobDto>(jobDtoReturn, HttpStatus.CREATED);
		return response;
	}
	
	@DeleteMapping("/{empId}/jobs/{jobId}")
	public ResponseEntity<Integer> deleteJob(@PathVariable Integer empId, @PathVariable Integer jobId){
		boolean isRemoved = employerService.deleteJob(empId, jobId);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jobId, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/jobs/{jobId}")
	public ResponseEntity<JobDto> searchJob(@PathVariable Integer jobId) throws UnavailableException {
		JobDto jobDtoReturn = employerService.searchJob(jobId);
		ResponseEntity<JobDto> response = new ResponseEntity<JobDto>(jobDtoReturn, HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{empId}/jobs/{jobId}")
	public ResponseEntity<JobDto> editJob(@PathVariable Integer empId, @PathVariable Integer jobId, @RequestBody @Valid Job job) throws UnavailableException {
		JobDto jobDtoReturn = employerService.editAJob(empId, jobId, job);
		ResponseEntity<JobDto> response = new ResponseEntity<JobDto>(jobDtoReturn, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/{empId}/jobs")
	public ResponseEntity<ArrayList<JobDto>> displayCreatedJobs(@PathVariable Integer empId) throws UnavailableException{
		ArrayList<JobDto> jobDtoListReturn = employerService.displayCreatedJobs(empId);
		ResponseEntity<ArrayList<JobDto>> response = new ResponseEntity<ArrayList<JobDto>>(jobDtoListReturn, HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{empId}")
	public ResponseEntity<EmployerDto> editEmployer(@RequestBody EmployerDto employerDto, @PathVariable Integer empId) throws UnavailableException {
		EmployerDto employerDtoReturn = employerService.updateEmployer(employerDto, empId);
		ResponseEntity<EmployerDto> response = new ResponseEntity<EmployerDto>(employerDtoReturn, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/jobseekersbyjob/{jobId}")
	public ResponseEntity<ArrayList<JobSeekerDto>> listJobSeekerByJob(@PathVariable Integer jobId) throws UnavailableException{
		ArrayList<JobSeekerDto> jobSeekerDtos = employerService.searchJobSeekerByJobId(jobId);
		ResponseEntity<ArrayList<JobSeekerDto>> response = new ResponseEntity<ArrayList<JobSeekerDto>>(jobSeekerDtos,HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{jsId}/jobapplstatus/{jobId}/{ord}")
	public ResponseEntity<String> changeApplicationStatus(@PathVariable Integer jsId, @PathVariable Integer jobId, @PathVariable Integer ord) throws UnavailableException {
		String response = employerService.changeApplStatus(jsId, jobId, ord);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
}
