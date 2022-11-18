package com.capgemini.sprint.jobsearchapp.dto.converters;

import com.capgemini.sprint.jobsearchapp.dto.ApplicationDto;
import com.capgemini.sprint.jobsearchapp.models.Application;

public class ApplicationDtoConverter {

//	public Application dtoToApplication(ApplicationDto applicationDto) {
//		
//	}
	
	public ApplicationDto applicationToDto(Application application) {
		ApplicationDto applicationDto = new ApplicationDto();
		applicationDto.setAppId(application.getAppId());
		applicationDto.setApplDate(application.getApplDate());
		applicationDto.setApplicationStatus(application.getApplicationStatus().toString());
		applicationDto.setJobSeekerId(application.getJobSeeker().getJsId());
		applicationDto.setJobId(application.getJob().getJobId());
		return applicationDto;
	}
}
