package com.capgemini.sprint.jobsearchapp.service.impl;

import java.util.ArrayList;

/**
 * @author Sidharth
 * @author Sai Deepak
 * @author Yaswanth
 * @author Bondi Shashank
 * @author Ashish T
 */

import java.util.List;
import java.util.Optional;

import javax.servlet.UnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.sprint.jobsearchapp.dto.EmployerDto;
import com.capgemini.sprint.jobsearchapp.dto.JobDto;
import com.capgemini.sprint.jobsearchapp.dto.JobSeekerDto;
import com.capgemini.sprint.jobsearchapp.dto.converters.EmployerDtoConverter;
import com.capgemini.sprint.jobsearchapp.dto.converters.JobDtoConverter;
import com.capgemini.sprint.jobsearchapp.dto.converters.JobSeekerDtoConverter;
import com.capgemini.sprint.jobsearchapp.models.Application;
import com.capgemini.sprint.jobsearchapp.models.ApplicationStatus;
import com.capgemini.sprint.jobsearchapp.models.Employer;
import com.capgemini.sprint.jobsearchapp.models.Job;
import com.capgemini.sprint.jobsearchapp.models.JobSeeker;
import com.capgemini.sprint.jobsearchapp.repo.AddressRepository;
import com.capgemini.sprint.jobsearchapp.repo.ApplicationRepository;
import com.capgemini.sprint.jobsearchapp.repo.EmployerRepository;
import com.capgemini.sprint.jobsearchapp.repo.JobRepository;
import com.capgemini.sprint.jobsearchapp.repo.JobSeekerRepository;
import com.capgemini.sprint.jobsearchapp.service.EmployerService;

@Service
public class EmployerServiceImpl implements EmployerService{

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	EmployerDtoConverter employerDtoConverter = new EmployerDtoConverter();
	
	JobSeekerDtoConverter jobSeekerDtoConverter = new JobSeekerDtoConverter();
	
	JobDtoConverter jobDtoConverter = new JobDtoConverter();

	@Override
	public EmployerDto createEmployer(EmployerDto employerDto) {
		Employer employer = employerDtoConverter.dtoToEmployer(employerDto); 
		addressRepository.save(employer.getAddress());
		employerRepository.save(employer);
		return employerDtoConverter.employerToDto(employer);
	}
	
	@Override
	public ArrayList<EmployerDto> displayAllEmployers() {
		List<Employer> employerList = employerRepository.findAll();
		ArrayList<EmployerDto> result = new ArrayList<EmployerDto>();
		for (Employer employer : employerList) {
			result.add(employerDtoConverter.employerToDto(employer));
		}
		return result;
	}
	
	@Override
	public void deleteEmployer(Integer empId) throws UnavailableException{
		Optional<Employer> employerOpt = employerRepository.findById(empId);
		if(!employerOpt.isPresent()) {
			throw new UnavailableException("Employer("+empId+") Not Found");
		}
		else {
			employerRepository.deleteById(empId);
		}
	
	}
	
	@Override
	public EmployerDto displayEmployer(Integer empId) throws UnavailableException {
		Optional<Employer> optEmployer = employerRepository.findById(empId);
		if(!optEmployer.isPresent()) {
			throw new UnavailableException("Employer("+empId+") Not Found");
		}
		return employerDtoConverter.employerToDto(optEmployer.get());
	}
	
	@Override
	public JobDto addJob(Integer empId, JobDto jobDto) throws UnavailableException{
		Optional<Employer> optEmployer = employerRepository.findById(empId);
		if(!optEmployer.isPresent()) {
			throw new UnavailableException("Employer("+empId+") Not Found");
		}
		Employer emp = optEmployer.get();
		Job job = jobDtoConverter.dtoToJob(jobDto, emp);
		job.setEmployer(emp);
		emp.getCreatedJobs().add(job);
		jobRepository.save(job);
		jobRepository.addEmployerOfJob(empId, job.getJobId());
		employerRepository.save(emp);
		return jobDtoConverter.jobToDto(job);
	}
	
	@Override
	public JobDto searchJob(Integer jobId) throws UnavailableException {
		Optional<Job> optJob = jobRepository.findById(jobId);
		if(!optJob.isPresent()) {
			throw new UnavailableException("Job("+jobId+") Not Found");
		}
		return jobDtoConverter.jobToDto(optJob.get());
	}
	
	@Override
	public boolean deleteJob(Integer empId, Integer jobId) {
		Employer employer = employerRepository.findById(empId).orElse(null);
		if(employer == null) {
			return false;
		}
		for (Job job : employer.getCreatedJobs()) {
			if(job.getJobId()==jobId) {
				jobRepository.deleteJob(jobId);
				return true;
			}
		}
		return false;
	}

	@Override
	public JobDto editAJob(Integer empId, Integer jobId, Job editedJob) throws UnavailableException {
		Optional<Employer> optEmployer = employerRepository.findById(empId);
		Optional<Job> optJob = jobRepository.findById(jobId);
		if(!optEmployer.isPresent()) {
			throw new UnavailableException("Employer("+empId+") Not Found");
		}
		else if(!optJob.isPresent()) {
			throw new UnavailableException("Job("+jobId+") Not Found");
		}
		else {
			Job job=optJob.get();
			if(!jobRepository.ifCreatedByEmployer(empId, jobId)) {
				JobDto jobDto = this.addJob(empId, jobDtoConverter.jobToDto(editedJob));
				return jobDto;
			}
			job.setTitle(editedJob.getTitle());
			job.setSalary(editedJob.getSalary());
			job.setLocation(editedJob.getLocation());
			job.setDescription(editedJob.getDescription());
			job.setMinExperience(editedJob.getMinExperience());
			job.setNoticePeriod(editedJob.getNoticePeriod());
			jobRepository.save(job);
			return jobDtoConverter.jobToDto(job);
		}
	}

	@Override
	public ArrayList<JobDto> displayCreatedJobs(Integer empId) throws UnavailableException {
		Optional<Employer> optEmployer = employerRepository.findById(empId);
		if(!optEmployer.isPresent()) {
			throw new UnavailableException("Employer("+empId+") Not Found");
		}
		List<Job> jobList = jobRepository.displayCreatedJobsByEmployer(empId);
		ArrayList<JobDto> result = new ArrayList<>();
		for (Job job : jobList) {
			result.add(jobDtoConverter.jobToDto(job));
		}
		return result;
	}
	
	@Override
	public EmployerDto updateEmployer(EmployerDto employerDto, Integer empId) throws UnavailableException{
		Optional<Employer> employerOpt = employerRepository.findById(empId);
		if(!employerOpt.isPresent()) {
			throw new UnavailableException("Employer("+empId+") Not Found");
		}
		Employer employer=employerOpt.get();
		employer.setEmpName(employerDto.getEmpName());
		employer.setEmpEmail(employerDto.getEmpEmail());
		employer.setEmpContactNo(employerDto.getEmpContactNo());
		employer.getAddress().setArea(employerDto.getArea());
		employer.getAddress().setDistrict(employerDto.getDistrict());
		employer.getAddress().setState(employerDto.getState());
		employer.getAddress().setPincode(employerDto.getPincode());
		addressRepository.save(employer.getAddress());
		employerRepository.save(employer);
		return employerDtoConverter.employerToDto(employer);
			
	}
	
	@Override
	public ArrayList<JobSeekerDto> searchJobSeekerByJobId(Integer jobId) throws UnavailableException{
		Optional<Job> optJob = jobRepository.findById(jobId);
		if(!optJob.isPresent()) {
			throw new UnavailableException("Job("+jobId+") Not Found");
		}
		List<JobSeeker> jobSeekerList = applicationRepository.getJobSeekersByJob(jobId);
		ArrayList<JobSeekerDto> result = new ArrayList<JobSeekerDto>();
		for (JobSeeker jobSeeker : jobSeekerList) {
			result.add(jobSeekerDtoConverter.jobSeekerToDto(jobSeeker));
		}
		return result;
	}

	@Override
	public String changeApplStatus(Integer jsId, Integer jobId, Integer ord) throws UnavailableException {
		Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findById(jsId);
		Optional<Job> jobOpt = jobRepository.findById(jobId);
		if(!jobSeekerOpt.isPresent()) {
			throw new UnavailableException("JobSeeker("+jsId+") Not Found");
		}
		else if(!jobOpt.isPresent()) {
			throw new UnavailableException("Job("+jobId+") Not Found");
		}
		Application application = applicationRepository.getApplication(jobId, jsId);
		if(application != null) {
			switch(ord) {
			case 0:
				application.setApplicationStatus(ApplicationStatus.APPLIED);
				break;
			case 1:
				application.setApplicationStatus(ApplicationStatus.REVIEW);
				break;
			case 2:
				application.setApplicationStatus(ApplicationStatus.SELECTED);
				break;
			case 3:
				application.setApplicationStatus(ApplicationStatus.REJECTED);
				break;
			default:
				throw new UnavailableException("ApplicationStatus for ordinal="+ord+" does not exist");
			}
			JobSeeker jobSeeker=jobSeekerOpt.get();
			Job job=jobOpt.get();
			job.getApplications().add(application);
			jobSeeker.getAppliedJobs().add(application);
			applicationRepository.save(application);
			jobRepository.save(job);
			jobSeekerRepository.save(jobSeeker);
			return "Status Changed to ApplicationStatus." + application.getApplicationStatus().toString();
		}else {
			throw new UnavailableException("JobSeeker("+jsId+") has not applied to Job("+jobId+")");
		}
	}
	
}
