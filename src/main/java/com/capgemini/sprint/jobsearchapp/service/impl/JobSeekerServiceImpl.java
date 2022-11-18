
package com.capgemini.sprint.jobsearchapp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Sidharth
 * @author Sai Deepak
 * @author Yaswanth
 * @author Bondi Shashank
 * @author Ashish T
 */

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.UnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.sprint.jobsearchapp.dto.ApplicationDto;
import com.capgemini.sprint.jobsearchapp.dto.JobDto;
import com.capgemini.sprint.jobsearchapp.dto.JobSeekerDto;
import com.capgemini.sprint.jobsearchapp.dto.converters.ApplicationDtoConverter;
import com.capgemini.sprint.jobsearchapp.dto.converters.JobDtoConverter;
import com.capgemini.sprint.jobsearchapp.dto.converters.JobSeekerDtoConverter;
import com.capgemini.sprint.jobsearchapp.models.Application;
import com.capgemini.sprint.jobsearchapp.models.ApplicationStatus;
import com.capgemini.sprint.jobsearchapp.models.Job;
import com.capgemini.sprint.jobsearchapp.models.JobSeeker;
import com.capgemini.sprint.jobsearchapp.repo.AddressRepository;
import com.capgemini.sprint.jobsearchapp.repo.ApplicationRepository;
import com.capgemini.sprint.jobsearchapp.repo.JobRepository;
import com.capgemini.sprint.jobsearchapp.repo.JobSeekerRepository;
import com.capgemini.sprint.jobsearchapp.service.JobSeekerService;

@Service
public class JobSeekerServiceImpl implements JobSeekerService{
	
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private JobSeekerRepository jobSeekerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ApplicationRepository applicationRepository;
	
	private JobSeekerDtoConverter jobSeekerDtoConverter = new JobSeekerDtoConverter();
	
	private JobDtoConverter jobDtoConverter = new JobDtoConverter();
	
	private ApplicationDtoConverter applicationDtoConverter = new ApplicationDtoConverter();
	
	@Override
	public JobSeekerDto createJobSeeker(JobSeekerDto jobSeekerDto) {
		JobSeeker jobSeeker = jobSeekerDtoConverter.dtoToJobSeeker(jobSeekerDto);
		addressRepository.save(jobSeeker.getAddress());
		jobSeekerRepository.save(jobSeeker);
		JobSeekerDto jobSeekerDtoReturn = jobSeekerDtoConverter.jobSeekerToDto(jobSeeker);
		return jobSeekerDtoReturn;
	}
	
	@Override
	public ArrayList<JobSeekerDto> displayAllJobSeeker() {
		List<JobSeeker> jobSeekerList = jobSeekerRepository.findAll();
		ArrayList<JobSeekerDto> result = new ArrayList<JobSeekerDto>();
		for (JobSeeker jobSeeker : jobSeekerList) {
			result.add(jobSeekerDtoConverter.jobSeekerToDto(jobSeeker));
		}
		return result;
	}
	
	@Override
	public void deleteJobSeeker(Integer jsId) throws UnavailableException{
		Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findById(jsId);
		if(!jobSeekerOpt.isPresent()) {
			throw new UnavailableException("jobSeeker("+jsId+") Not Found");
		}
		else {
			for (Application application : jobSeekerOpt.get().getAppliedJobs()) {
				applicationRepository.delete(application);
			}
			jobSeekerRepository.deleteJobSeekerById(jsId);
		}
	}
	
	@Override
	public JobSeekerDto updateJobSeeker(Integer jsId, JobSeekerDto jobSeekerDto) throws UnavailableException {
		Optional<JobSeeker> optJobSeeker = jobSeekerRepository.findById(jsId);
		if(!optJobSeeker.isPresent()) {
			throw new UnavailableException("jobSeeker("+jsId+") Not Found");
		}
		JobSeeker jobSeeker = optJobSeeker.get();
		jobSeeker.getAddress().setArea(jobSeekerDto.getArea());
		jobSeeker.getAddress().setDistrict(jobSeekerDto.getDistrict());
		jobSeeker.getAddress().setState(jobSeekerDto.getState());
		jobSeeker.getAddress().setPincode(jobSeekerDto.getPincode());
		jobSeeker.setJsName(jobSeekerDto.getJsName());
		jobSeeker.setJsContactNo(Long.valueOf(jobSeekerDto.getJsContactNo()));
		jobSeeker.setJsEmail(jobSeekerDto.getJsEmail());
		addressRepository.save(jobSeeker.getAddress());
		jobSeekerRepository.save(jobSeeker);
		JobSeekerDto jobSeekerDtoReturn = jobSeekerDtoConverter.jobSeekerToDto(jobSeeker);
		return jobSeekerDtoReturn;
	}
	
	@Override
	public JobSeekerDto display(Integer jsId) throws UnavailableException {
		Optional<JobSeeker> optJobSeeker = jobSeekerRepository.findById(jsId);
		if(!optJobSeeker.isPresent()) {
			throw new UnavailableException("jobSeeker("+jsId+") Not Found");
		}
		return jobSeekerDtoConverter.jobSeekerToDto(optJobSeeker.get());
	}
	
	@Override
	public JobDto displayJob(Integer jobId) throws UnavailableException{
		Optional<Job> jobOpt = jobRepository.findById(jobId);
		if(!jobOpt.isPresent()) {
			throw new UnavailableException("Job("+jobId+") Not Found");
		}
		JobDto jobDto = jobDtoConverter.jobToDto(jobOpt.get());
		return jobDto;
	}
	
	@Override
	public String applyToJob(Integer jsId, Integer jobId) throws UnavailableException{
		Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findById(jsId);
		Optional<Job> jobOpt = jobRepository.findById(jobId);	
		if(!jobSeekerOpt.isPresent()) {
			throw new UnavailableException("JobSeeker("+jsId+") Not Found");
		}
		else if(!jobOpt.isPresent()) {
			throw new UnavailableException("Job("+jobId+") Not Found");
		}
		else {
			if(this.getApplStatus(jsId, jobId).compareTo("NOT APPLIED")==0) {
				JobSeeker jobSeeker=jobSeekerOpt.get();
				Job job=jobOpt.get();
				Application application = new Application(job, jobSeeker);
				job.getApplications().add(application);
				jobSeeker.getAppliedJobs().add(application);
				applicationRepository.save(application);
				jobRepository.save(job);
				jobSeekerRepository.save(jobSeeker);
				return "SUCCESS";
			}else {
				return this.getApplStatus(jsId, jobId);
			}
			
		}
	}

	@Override
	public Boolean withdrawJob(Integer jsId, Integer jobId) throws UnavailableException{
		if (jobSeekerRepository.existsById(jsId) && jobRepository.existsById(jobId)) {
			if(this.getApplStatus(jsId, jobId).compareTo("NOT APPLIED")==0) {
				throw new UnavailableException("JobSeeker("+jsId+") has not applied to Job("+jobId+")");
			}
			Job job = jobRepository.findById(jobId).get();
			List<Job> applJobList = jobRepository.findJobsByJobSeeker(jsId);
			for (Job applJob : applJobList) {
				if (applJob.getJobId() == job.getJobId()) {
					applicationRepository.deleteApplicationByJobId(jobId,jsId);
					return true;
				}
			}
		}else {
			Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findById(jsId);
			Optional<Job> jobOpt = jobRepository.findById(jobId);	
			if(!jobSeekerOpt.isPresent()) {
				throw new UnavailableException("JobSeeker("+jsId+") Not Found");
			}
			else if(!jobOpt.isPresent()) {
				throw new UnavailableException("Job("+jobId+") Not Found");
			}
		}
		return false;
	}
	
	@Override
	public ArrayList<JobDto> displayAllJobs() {
		ArrayList<JobDto> result = new ArrayList<>();
		List<Job> jobList = jobRepository.findAll();
		for (Job job : jobList) {
			result.add(jobDtoConverter.jobToDto(job));
		}
		return result;
	}
	
	@Override
	public ArrayList<JobDto> displayAppliedJobs(Integer jsId){
		List<Job> jobList = jobRepository.displayAppliedJobsByJobSeeker(jsId);
		ArrayList<JobDto> result = new ArrayList<JobDto>();
		for (Job job : jobList) {
			result.add(jobDtoConverter.jobToDto(job));
		}
		return result;
	}

	@Override
	public JobDto addJobToBasket(Integer jsId, Integer jobId) throws UnavailableException{
		Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findById(jsId);
		Optional<Job> jobOpt = jobRepository.findById(jobId);
		if(!jobSeekerOpt.isPresent()) {
			throw new UnavailableException("JobSeeker("+jsId+") Not Found");
		}
		else if(!jobOpt.isPresent()) {
			throw new UnavailableException("Job("+jobId+") Not Found");
		}
		else {
			JobSeeker jobSeeker=jobSeekerOpt.get();
			Job job=jobOpt.get();
			job.getBaskets().add(jobSeeker);
			jobRepository.save(job);
			jobSeekerRepository.save(jobSeeker);
			return jobDtoConverter.jobToDto(job);
		}
	}

	@Override
	public JobDto deleteJobFromBasket(Integer jsId, Integer jobId) throws UnavailableException {
		Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findById(jsId);
		Optional<Job> jobOpt = jobRepository.findById(jobId);
		if(!jobSeekerOpt.isPresent()) {
			throw new UnavailableException("JobSeeker("+jsId+") Not Found");
		}
		else if(!jobOpt.isPresent()) {
			throw new UnavailableException("Job("+jobId+") Not Found");
		}
		else {
			Job job = jobOpt.get();
			JobSeeker jobSeeker = jobSeekerOpt.get();
			job.getBaskets().remove(jobSeeker);
			jobRepository.save(job);
			jobSeekerRepository.save(jobSeeker);
			return jobDtoConverter.jobToDto(job);
		}
	}
	
	@Override
	public ArrayList<JobDto> applyAllBasketJobs(Integer jsId) throws UnavailableException {
		Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findById(jsId);
		if(!jobSeekerOpt.isPresent()) {
			throw new UnavailableException("JobSeeker("+jsId+") Not Found");
		}
		JobSeeker jobSeeker = jobSeekerOpt.get();
		ArrayList<Integer> appliedJobIds = new ArrayList<Integer>
				(jobRepository.displayAppliedJobsByJobSeeker(jsId)
				.stream().map(job -> job.getJobId()).collect(Collectors.toList()));
		Iterator<Job> iterator = jobSeeker.getBasketJobs().iterator();
		while(iterator.hasNext()) {
			Job job = iterator.next();
			if (!appliedJobIds.contains(job.getJobId())) {
				Application application = new Application(job, jobSeeker);
				job.getApplications().add(application);
				jobSeeker.getAppliedJobs().add(application);
				applicationRepository.save(application);
				jobRepository.save(job);
				jobSeekerRepository.save(jobSeeker);
			}
		}
		iterator = jobSeeker.getBasketJobs().iterator();
		while(iterator.hasNext()) {
			Job job = iterator.next();
			job.getBaskets().removeAll(job.getBaskets());
			jobRepository.save(job);
		}
		jobSeeker.getBasketJobs().removeAll(jobSeeker.getBasketJobs());
		jobSeekerRepository.save(jobSeeker);
		return this.displayAppliedJobs(jsId);
	}
	
	@Override
	public ArrayList<JobDto> getJobByLocation(String location) {
		List<Job> jobs = jobRepository.findAll()
				.stream()
				.filter(job -> job.getLocation().toLowerCase().contains(location.toLowerCase()))
				.collect(Collectors.toList());
		ArrayList<JobDto> result = new ArrayList<JobDto>();
		for (Job listJob : jobs) {
			result.add(jobDtoConverter.jobToDto(listJob));
		}
		return result;
	}

	@Override
	public String getApplStatus(Integer jsId, Integer jobId) throws UnavailableException {
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
			String applStatus = application.getApplicationStatus().toString();
			return applStatus;
		}else {
			return "NOT APPLIED";
		}
	}

	@Override
	public ApplicationDto getApplication(Integer jsId, Integer jobId) throws UnavailableException {
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
			ApplicationDto applicationDto = applicationDtoConverter.applicationToDto(application);
			return applicationDto;
		}else {
			throw new UnavailableException("JobSeeker("+jsId+") has not applied to Job("+jobId+")");
		}
	}
	
	@Override
	public ArrayList<JobDto> displayBasketJobs(Integer jsId) throws UnavailableException {
		Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findById(jsId);
		if(!jobSeekerOpt.isPresent()) {
			throw new UnavailableException("JobSeeker("+jsId+") Not Found");
		}
		JobSeeker jobSeeker=jobSeekerOpt.get();
		ArrayList<JobDto> jobs = new ArrayList<JobDto>();
		for (Job job : jobSeeker.getBasketJobs()) {
			jobs.add(jobDtoConverter.jobToDto(job));
		}
		return jobs;
	}
}
