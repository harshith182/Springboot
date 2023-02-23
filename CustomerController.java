package com.BankLoanApplication.controller;

import javax.validation.ConstraintViolationException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BankLoanApplication.exception.CustomerException;
import com.BankLoanApplication.modelclass.Customer;
import com.BankLoanApplication.repository.CustomerRepository;
import com.BankLoanApplication.service.CustomerService;

@RestController
public class CustomerController
{
	
//	@Autowired
//	private CustomerRepository customerRepo;
	@Autowired
	private CustomerService customerservice;
	
	 @PostMapping("/savecustomers")
	  public ResponseEntity<?> createcustomer(@RequestBody Customer customer){
		  try {
		  customerservice.createcustomer(customer);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	  }
		catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		  catch(CustomerException e) {
			  return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		  }
}

}
