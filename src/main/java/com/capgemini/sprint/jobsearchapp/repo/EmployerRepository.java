package com.capgemini.sprint.jobsearchapp.repo;

/**
 * @author Sidharth
 * @author Sai Deepak
 * @author Yaswanth
 * @author Bondi Shashank
 * @author Ashish T
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.sprint.jobsearchapp.models.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

}
