package com.capgemini.sprint.jobsearchapp.repo;

import java.util.List;

/**
 * @author Sidharth
 * @author Sai Deepak
 * @author Yaswanth
 * @author Bondi Shashank
 * @author Ashish T
 */

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.sprint.jobsearchapp.models.Application;
import com.capgemini.sprint.jobsearchapp.models.JobSeeker;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Transactional
	@Modifying
	@Query("delete from Application application where application.job IN"
			+ " (select job from Job job where job.jobId = :jobId) AND application.jobSeeker IN"
			+ " (select jobSeeker from JobSeeker jobSeeker where jobSeeker.jsId = :jsId)")
	void deleteApplicationByJobId(@Param("jobId") Integer jobId, @Param("jsId") Integer jsId);
	
	@Transactional
	@Query("select application FROM Application application "
			+ "JOIN application.jobSeeker jobSeeker JOIN application.job job WHERE "
			+ "jobSeeker.jsId = :jsId AND job.jobId = :jobId")
	Application getApplication(@Param("jobId") Integer jobId, @Param("jsId") Integer jsId);
	
	@Query("select jobSeeker from Application application "
			+ "JOIN application.jobSeeker jobSeeker JOIN application.job job WHERE "
			+ "job.jobId = :jobId")
	List<JobSeeker> getJobSeekersByJob(@Param("jobId") Integer jobId);
	
}
