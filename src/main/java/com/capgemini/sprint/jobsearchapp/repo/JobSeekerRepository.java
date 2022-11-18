package com.capgemini.sprint.jobsearchapp.repo;

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

import com.capgemini.sprint.jobsearchapp.models.JobSeeker;

/**
 * @NoRepositoryBean
 * JPA specific extension of org.springframework.data.repository.Repository.JpaRepository
 */
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {
	
	/**
	 * @Transactional
	 * @Modifying
	 * @Query "delete from JobSeeker jobSeeker where jobSeeker.jsId = :jsId"
	 * @param jsId
	 */
	@Transactional
	@Modifying
	@Query("delete from JobSeeker jobSeeker where jobSeeker.jsId = :jsId")
	void deleteJobSeekerById(@Param("jsId") Integer jsId);
}