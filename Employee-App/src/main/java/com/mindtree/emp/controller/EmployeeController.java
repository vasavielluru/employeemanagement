package com.mindtree.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.emp.entity.Employee;
import com.mindtree.emp.exception.daoexception.EmployeeNotFoundDaoException;
import com.mindtree.emp.exception.serviceException.EmployeeInputServiceException;
import com.mindtree.emp.exception.serviceException.EmployeeNotFoundServiceException;
import com.mindtree.emp.exception.serviceException.IntenalServerServiceException;
import com.mindtree.emp.service.EmployeeService;
import com.sun.istack.NotNull;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService emp;
	@GetMapping("/Employees")
	public ResponseEntity<List<Employee>> getEmployees() throws EmployeeInputServiceException, IntenalServerServiceException{
		
		try {
			return emp.getEmployees();
		} catch (EmployeeInputServiceException e) {
			// TODO Auto-generated catch block
			throw new EmployeeInputServiceException(e);
		} catch (IntenalServerServiceException e) {
			// TODO Auto-generated catch block
			throw new IntenalServerServiceException(e);
		}
		
	}
	
	@PostMapping(value = "/Employees" )
	public ResponseEntity<String> addHotel(@RequestBody Employee empadd) throws EmployeeInputServiceException, IntenalServerServiceException {
		try {
			try {
				return emp.addHotel(empadd);
			} catch (IntenalServerServiceException e) {
				throw new IntenalServerServiceException(e.getLocalizedMessage());
			}
		} catch (EmployeeInputServiceException e) {
			// TODO Auto-generated catch block
			throw new EmployeeInputServiceException(e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/Employees/{value}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("value") @NotNull int id) throws EmployeeNotFoundServiceException, EmployeeInputServiceException, IntenalServerServiceException {
		System.out.println(id);
			try {
				return emp.getEmployeeById(id);
			} catch (EmployeeNotFoundServiceException e) {
				// TODO Auto-generated catch block
				throw new EmployeeNotFoundServiceException(e);
			}
			catch (EmployeeInputServiceException e) {
				// TODO Auto-generated catch block
				throw new EmployeeInputServiceException(e.getLocalizedMessage());
			}
			catch (IntenalServerServiceException e) {
				// TODO: handle exception
				throw new IntenalServerServiceException(e);
			}
	}
	
	@DeleteMapping("/Employees/{value}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("value") int id) throws EmployeeNotFoundServiceException {
			try {
				return emp.deleteById(id);
			} catch (EmployeeNotFoundServiceException e) {
				// TODO Auto-generated catch block
				throw new EmployeeNotFoundServiceException(e);
			}
	}
	
	@PutMapping("/Employees/{value}")
	public ResponseEntity<String> updateSalary(@RequestBody float empSalary ,@PathVariable("value") int id) throws EmployeeNotFoundServiceException {
			try {
				return 	emp.updateSalary(empSalary,id);
			} catch (EmployeeNotFoundServiceException e) {
				// TODO Auto-generated catch block
				throw new EmployeeNotFoundServiceException(e);
			}
	}
}
