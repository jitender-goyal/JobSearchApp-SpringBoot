package com.capgemini.sprint.jobsearchapp.repo;

/**
 * @author Sidharth
 * @author Sai Deepak
 * @author Yaswanth
 * @author Bondi Shashank
 * @author Ashish T
 */

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.sprint.jobsearchapp.models.Job;

/**
 * @NoRepositoryBean
 * JPA specific extension of org.springframework.data.repository.Repository.JpaRepository
 */
public interface JobRepository extends JpaRepository<Job, Integer>{

	/**
	 * Returns the list of jobs applied by a jobseeker
	 * @param jsId
	 * @return List<Job>
	 */
	@Query("select job from Job job join job.applications appl where appl IN "
			+ "(select application from Application application join application.jobSeeker jobSeeker where jobSeeker IN "
			+ "(select jobSeeker from JobSeeker jobSeeker where jobSeeker.jsId = :jsId))")
	List<Job> findJobsByJobSeeker(@Param("jsId") Integer jsId);
	
	/**
	 * @Modifying
	 * @Transactional
	 * maps job and employer entities
	 * @param empId
	 * @param jobId
	 */
	@Modifying
	@Transactional
	@Query("update Job job set job.employer = (select employer from Employer employer where employer.empId = :empId) where job.jobId = :jobId")
	void addEmployerOfJob(@Param("empId") Integer empId, @Param("jobId") Integer jobId);
	
	/**
	 * @Modifying
	 * @Transactional
	 * Deletes job
	 * @param jobId
	 */
	@Modifying
	@Transactional
	@Query("delete Job job where job.jobId = :jobId")
	void deleteJob(@Param("jobId") Integer jobId);
	
	/**
	 * @param empId
	 * @param jobId
	 * @return true if job created by employer, else false
	 */
	@Query("select case when (count(job) > 0) then true else false end from Job job join job.employer employer "
			+ "where job.jobId = :jobId AND employer.empId = :empId")
	Boolean ifCreatedByEmployer(@Param("empId") Integer empId, @Param("jobId") Integer jobId);
	
	/**
	 * 
	 * @param jsId
	 * @return List<Job> jobs applied by jobseeker
	 */
	@Query("select job from Job job join job.applications appl where appl IN "
			+ "(select appl from Application appl join appl.jobSeeker jobSeeker where jobSeeker.jsId = :jsId)")
	List<Job> displayAppliedJobsByJobSeeker(@Param("jsId") Integer jsId);
	
	/**
	 * @param empId
	 * @return List<Job> jobs created by employer
	 */
	@Query("select job from Job job join job.employer employer where employer.empId = :empId")
	List<Job> displayCreatedJobsByEmployer(@Param("empId") Integer empId);
}
