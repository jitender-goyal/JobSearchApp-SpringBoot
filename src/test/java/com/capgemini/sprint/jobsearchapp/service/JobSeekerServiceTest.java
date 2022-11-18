package com.capgemini.sprint.jobsearchapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.capgemini.sprint.jobsearchapp.dto.JobSeekerDto;
import com.capgemini.sprint.jobsearchapp.dto.converters.JobSeekerDtoConverter;
import com.capgemini.sprint.jobsearchapp.models.JobSeeker;
import com.capgemini.sprint.jobsearchapp.repo.AddressRepository;
import com.capgemini.sprint.jobsearchapp.repo.JobRepository;
import com.capgemini.sprint.jobsearchapp.repo.JobSeekerRepository;
import com.capgemini.sprint.jobsearchapp.service.impl.JobSeekerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class JobSeekerServiceTest {

	@InjectMocks
	JobSeekerServiceImpl jobSeekerService;
	
	@Mock
	JobSeekerRepository jobSeekerRepository;
	
	@Mock
	JobRepository jobRepository;
	
	@Mock
	AddressRepository addressRepository;
	
	JobSeekerDtoConverter jobSeekerDtoConverter = new JobSeekerDtoConverter();
	
	@Test
	void testGetAllJobSeekers() {
		ArrayList<JobSeeker> jobSeekers = getMockJobSeekers();
		Mockito.when(jobSeekerRepository.findAll()).thenReturn(jobSeekers);
		
		ArrayList<JobSeekerDto> testJobSeekers = jobSeekerService.displayAllJobSeeker();
		
		assertNotNull(testJobSeekers);
		assertEquals(3, testJobSeekers.size());
	}
	
	
	
	private ArrayList<JobSeeker> getMockJobSeekers(){
		ArrayList<JobSeeker> jsList = new ArrayList<JobSeeker>();
		JobSeeker mockJS1 = jobSeekerDtoConverter.dtoToJobSeeker(
				new JobSeekerDto(12,
				"Sneha",
				"9787627596",
				"snpass",
				"sneha@gmail.com",
				"Kakkanad",
				"Kochi",
				"Kerala",
				160021,
				new ArrayList<Integer>(Arrays.asList(4,7,16,32,14)),
				new ArrayList<Integer>()));
		JobSeeker mockJS2 = jobSeekerDtoConverter.dtoToJobSeeker(
				new JobSeekerDto(15,
				"Kabilan",
				"8890409908",
				"kbpass",
				"kabil@gmail.com",
				"Swaraj Ashram",
				"Bardoli",
				"Gujarat",
				394601,
				new ArrayList<Integer>(Arrays.asList(21,11)),
				new ArrayList<Integer>(Arrays.asList(6,3,9))));
		JobSeeker mockJS3 = jobSeekerDtoConverter.dtoToJobSeeker(
				new JobSeekerDto(25,
				"Mahesh",
				"7990588809",
				"mhpass",
				"mahesh@gmail.com",
				"Miramar",
				"Panjim",
				"Goa",
				403001,
				new ArrayList<Integer>(Arrays.asList(15)),
				new ArrayList<Integer>(Arrays.asList(8,17))));
		jsList.add(mockJS1);
		jsList.add(mockJS2);
		jsList.add(mockJS3);
		return jsList;
	}
	
	
//	@Test
//	void testGetAllJobSeekers() {
//		
//		List<JobSeeker> jobSeekers = getJobSeekersMockData();
//		Mockito.when(jobSeekerRepository.findAll()).thenReturn(jobSeekers);
//		
//		HashMap<String, HashMap<String, Object>> jsInfo = jobSeekerService.displayAllJobSeeker();
//		
//		Assertions.assertEquals(1, jsInfo.size());
//	}
//	/**
//	 * JobSeeker(Integer jsId, @NotBlank(message = "Name must not be empty or null") String jsName,
//			@NotNull Long jsContactNo, @NotBlank(message = "UseName must not be empty or null") String jsUserName,
//			@NotBlank(message = "Password must not be empty or null") String jsPassword,
//			@NotBlank(message = "Email must not be empty or null") @Email String jsEmail, @Valid Address address,
//			Set<Application> appliedJobs, Set<Job> basketJobs)
//	 * @return
//	 */
//	private List<JobSeeker> getJobSeekersMockData(){
//		List<JobSeeker> jobSeekers = new ArrayList<>();
//		Address add1=new Address("Ghandhi road", "Kadapa", "Andhra", 516360);
//		Address add2=new Address("JN Road", "Vadapalani", "TN", 624521);
//		JobSeeker js1 = new JobSeeker(12, "Yaswanth", "Pass1234", "yash@capgemini.com", 7963473528l, add1);
//		JobSeeker js2 = new JobSeeker("Sidharth", "password", "sid@capgemini.com", 9944931032l, add2);
//		jobSeekers.add(js1);
//		jobSeekers.add(js2);
//		return jobSeekers;
//	}
//	
//	@Test
//	void testCreateJobSeeker() {
//		
//		Optional<JobSeeker> jsOpt = getJobSeekerMockData();
//		JobSeeker js = jsOpt.get();
//		Mockito.when(addressRepository.save(js.getAddress())).thenReturn(js.getAddress());
//		Mockito.when(jobSeekerRepository.save(js)).thenReturn(js);
//		
//		JobSeeker jobSeeker = jobSeekerService.createJobSeeker(js);
//
//		Assertions.assertTrue(jobSeeker.getJsName().equalsIgnoreCase("Sidharth"));
//		Assertions.assertTrue(jobSeeker.getJsEmail().equalsIgnoreCase("sneha@capgemini.com"));
//	}
//	
//	private Optional<JobSeeker> getJobSeekerMockData(){
//		Set<Skill> skillSet1 = new HashSet<>();
//		skillSet1.add(new Skill("java"));
//		skillSet1.add(new Skill("react"));
//		Address add=new Address("Shivan Road", "Selam", "Tamil Nadu", 654225);
//		JobSeeker js = new JobSeeker("Sneha", "Sneha005", "Pass7785", "sneha@capgemini.com", 7855452216L, add, skillSet1);
//		return Optional.of(js);
//	}
//	
//	private List<Job> getJobsMockData(){
//		Set<Skill> skillSet1 = new HashSet<>();
//		skillSet1.add(new Skill("java"));
//		skillSet1.add(new Skill("react"));
//		Set<Skill> skillSet2 = new HashSet<>();
//		skillSet2.add(new Skill("python"));
//		skillSet2.add(new Skill("management"));
//		Optional<Employer> empOpt = getEmployerMockData();
//		Employer emp = empOpt.get();
//		Job job1 = new Job("Dev","Hydrabad","To code",52000D,2,6,emp,skillSet1);
//		Job job2 = new Job("Manager","Hydrabad","To manage",105000D,6,6,emp,skillSet2);
//		List<Job> jobs = new ArrayList<>();
//		jobs.add(job1);
//		jobs.add(job2);
//		return jobs;
//	}
//	
//	private Optional<Employer> getEmployerMockData(){
//		Address add=new Address("Shivan Road", "Selam", "Tamil Nadu", 654225);
//		Employer emp = new Employer("Capgemini", "cap005", "Pass7785", "contact@capgemini.com", 7855452216l, add);
//		return Optional.of(emp);
//	}
//	
//	@Test
//	void testDisplayAllJobs() {
//		List<Job> jobs = getJobsMockData();
//		Mockito.when(jobRepository.findAll()).thenReturn(jobs);
//		
//		HashMap<String, HashMap<String, Object>> allJobs = jobSeekerService.displayAllJobs();
//		
//		Assertions.assertEquals(2, allJobs.size());
//		
//	}
	
}
