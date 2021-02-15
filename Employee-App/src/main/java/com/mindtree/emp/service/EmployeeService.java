package com.mindtree.emp.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mindtree.emp.entity.Employee;
import com.mindtree.emp.exception.serviceException.EmployeeInputServiceException;
import com.mindtree.emp.exception.serviceException.EmployeeNotFoundServiceException;
import com.mindtree.emp.exception.serviceException.IntenalServerServiceException;
import com.mindtree.emp.util.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository emp;

	public ResponseEntity<List<Employee>> getEmployees()
			throws EmployeeInputServiceException, IntenalServerServiceException {
		// TODO Auto-generated method stub
		System.out.println(emp.findAll().get(1));
		try {
			if (emp.findAll().get(1)!=null) {
				return new ResponseEntity<List<Employee>>(emp.findAll(), HttpStatus.OK);
			} else {
				throw new EmployeeInputServiceException("No Employess");
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new IntenalServerServiceException("Server error");
		}
	}

	public ResponseEntity<String> addHotel(Employee empadd)
			throws IntenalServerServiceException, EmployeeInputServiceException {
		// TODO Auto-generated method stub
		try {
			if (emp.save(empadd).getEmpName() != null) {
				return new ResponseEntity<>("Employee is Added", HttpStatus.CREATED);
			} else {
				throw new EmployeeInputServiceException("Insertion Error ");
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new IntenalServerServiceException("Server error");
		}
	}

	public ResponseEntity<Employee> getEmployeeById(int id)
			throws EmployeeNotFoundServiceException, EmployeeInputServiceException, IntenalServerServiceException {
		try {
			return new ResponseEntity<>(emp.findById(id).get(), HttpStatus.OK);
			}
			catch (MethodArgumentTypeMismatchException | NumberFormatException e) {
				throw new EmployeeInputServiceException("Please enter integer value ");
			}
			catch (NoSuchElementException e) {
				throw new EmployeeNotFoundServiceException("Employee not found");
			}
			catch (RuntimeException e) {
				throw new IntenalServerServiceException("Server Exception");
			}
	}

	public ResponseEntity<String> deleteById(int id) throws EmployeeNotFoundServiceException {
		// TODO Auto-generated method stub
		Employee deleteEmp =emp.findById(id).orElseThrow( ()->new EmployeeNotFoundServiceException("Employee not found"));
		emp.delete(deleteEmp);
		return new ResponseEntity<>("Employee deleted successfully", HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<String> updateSalary(float upsalary, int id) throws EmployeeNotFoundServiceException {
		// TODO Auto-generated method stub
		Employee updatedEmp = emp.findById(id).orElseThrow( ()->new EmployeeNotFoundServiceException("Employee not found"));
		updatedEmp.setEmpSalary(upsalary);
		emp.save(updatedEmp);
		return new ResponseEntity<>("Salary updated successfully", HttpStatus.OK);
	}
}
