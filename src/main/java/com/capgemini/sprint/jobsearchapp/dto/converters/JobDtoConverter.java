package com.capgemini.sprint.jobsearchapp.dto.converters;

import com.capgemini.sprint.jobsearchapp.dto.JobDto;
import com.capgemini.sprint.jobsearchapp.models.Application;
import com.capgemini.sprint.jobsearchapp.models.Employer;
import com.capgemini.sprint.jobsearchapp.models.Job;

public class JobDtoConverter {
	public Job dtoToJob(JobDto jobDto, Employer employer) {
		Job job = new Job();
		job.setTitle(jobDto.getTitle());
		job.setLocation(jobDto.getLocation());
		job.setSalary(jobDto.getSalary());
		job.setNoticePeriod(jobDto.getNoticePeriod());
		job.setDescription(jobDto.getDescription());
		job.setMinExperience(jobDto.getMinExperience());
		job.setEmployer(employer);
		return job;
	}
	
	public JobDto jobToDto(Job job) {
		JobDto jobDto = new JobDto();
		jobDto.setJobId(job.getJobId());
		jobDto.setTitle(job.getTitle());
		jobDto.setSalary(job.getSalary());
		jobDto.setDescription(job.getDescription());
		jobDto.setMinExperience(job.getMinExperience());
		jobDto.setNoticePeriod(job.getNoticePeriod());
		jobDto.setLocation(job.getLocation());
		jobDto.setEmployerId(job.getEmployer().getEmpId());
		for (Application application : job.getApplications()) {
			jobDto.getApplications().add(application.getAppId());
		}
		return jobDto;
	}
}
