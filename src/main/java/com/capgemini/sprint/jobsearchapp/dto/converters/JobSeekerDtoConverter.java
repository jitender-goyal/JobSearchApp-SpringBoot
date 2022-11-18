package com.capgemini.sprint.jobsearchapp.dto.converters;

import java.util.ArrayList;
import java.util.List;
import com.capgemini.sprint.jobsearchapp.dto.JobSeekerDto;
import com.capgemini.sprint.jobsearchapp.models.Address;
import com.capgemini.sprint.jobsearchapp.models.Application;
import com.capgemini.sprint.jobsearchapp.models.Job;
import com.capgemini.sprint.jobsearchapp.models.JobSeeker;

public class JobSeekerDtoConverter {
	
	public JobSeeker dtoToJobSeeker(JobSeekerDto jobSeekerDto) {
		Address address = new Address();
		address.setArea(jobSeekerDto.getArea());
		address.setDistrict(jobSeekerDto.getDistrict());
		address.setState(jobSeekerDto.getState());
		address.setPincode(jobSeekerDto.getPincode());
		JobSeeker jobSeeker = new JobSeeker();
		jobSeeker.setAddress(address);
		jobSeeker.setJsName(jobSeekerDto.getJsName());
		jobSeeker.setJsPassword(jobSeekerDto.getJsPassword());
		jobSeeker.setJsContactNo(Long.valueOf(jobSeekerDto.getJsContactNo()));
		jobSeeker.setJsEmail(jobSeekerDto.getJsEmail());
		return jobSeeker;
	}
	
	public JobSeekerDto jobSeekerToDto(JobSeeker jobSeeker) {
		JobSeekerDto jobSeekerDto = new JobSeekerDto();
		jobSeekerDto.setJsId(jobSeeker.getJsId());
		jobSeekerDto.setJsName(jobSeeker.getJsName());
		jobSeekerDto.setJsEmail(jobSeeker.getJsEmail());
		jobSeekerDto.setJsPassword(jobSeeker.getJsPassword());
		jobSeekerDto.setJsContactNo(String.valueOf(jobSeeker.getJsContactNo()));
		jobSeekerDto.setArea(jobSeeker.getAddress().getArea());
		jobSeekerDto.setDistrict(jobSeeker.getAddress().getDistrict());
		jobSeekerDto.setState(jobSeeker.getAddress().getState());
		jobSeekerDto.setPincode(jobSeeker.getAddress().getPincode());
		List<Integer> applList = new ArrayList<>();
		for (Application application : jobSeeker.getAppliedJobs()) {
			applList.add(application.getAppId());
		}
		List<Integer> basketList = new ArrayList<>();
		for (Job job : jobSeeker.getBasketJobs()) {
			basketList.add(job.getJobId());
		}
		return jobSeekerDto;
	}
}