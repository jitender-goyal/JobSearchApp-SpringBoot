package com.capgemini.sprint.jobsearchapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capgemini.sprint.jobsearchapp.models.Address;
import com.capgemini.sprint.jobsearchapp.models.Employer;
import com.capgemini.sprint.jobsearchapp.repo.AddressRepository;
import com.capgemini.sprint.jobsearchapp.repo.EmployerRepository;
import com.capgemini.sprint.jobsearchapp.service.impl.EmployerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployerServiceTest {

	@InjectMocks
	EmployerServiceImpl employerService;
	
	@Mock
	EmployerRepository employerRepository;
	
	@Mock
	AddressRepository addressRepository;
	
//	@Test
//	void testGetAllEmployers() {
//		
//		List<Employer> employers = getEmployersMockData();
//		Mockito.when(employerRepository.findAll()).thenReturn(employers);
//		
//		HashMap<String, HashMap<String, Object>> empInfo = employerService.displayAllEmployers();
//		
//		Assertions.assertEquals(2, empInfo.size());
//		
//	}
//	
//	private List<Employer> getEmployersMockData(){
//		List<Employer> employers = new ArrayList<>();
//		Address add1=new Address("Ghandhi road", "Kadapa", "Andhra", 516360);
//		Address add2=new Address("JN Road", "Vadapalani", "TN", 624521);
//		Employer emp1 = new Employer("Yaswanth", "Yaswanth002", "Pass1234", "yash@capgemini.com", 7963473528l, add1);
//		Employer emp2 = new Employer("Sidharth", "sidhu40", "password", "sid@capgemini.com", 9944931032l, add2);
//		employers.add(emp1);
//		employers.add(emp2);
//		return employers;
//	}
//	
//	@Test
//	void testCreateEmployer() {
//		
//		Optional<Employer> empOpt = getEmployerMockData();
//		Employer emp = empOpt.get();
//		Mockito.when(addressRepository.save(emp.getAddress())).thenReturn(emp.getAddress());
//		Mockito.when(employerRepository.save(emp)).thenReturn(emp);
//		
//		Employer employer = employerService.createEmployer(emp);
////		System.out.println(employer.getEmpName());
////		System.out.println(employer.getEmpName().equalsIgnoreCase("Sidharth"));
//		
//		Assertions.assertTrue(employer.getEmpName().equalsIgnoreCase("Sidharth"));
//		
//	}
//	
//	private Optional<Employer> getEmployerMockData(){
//		Address add=new Address("Shivan Road", "Selam", "Tamil Nadu", 654225);
//		Employer emp = new Employer("Capgemini", "cap005", "Pass7785", "contact@capgemini.com", 7855452216l, add);
//		return Optional.of(emp);
//	}
}
