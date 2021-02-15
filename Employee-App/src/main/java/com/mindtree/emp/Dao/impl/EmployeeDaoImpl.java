package com.mindtree.emp.Dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mindtree.emp.Dao.EmployeeDao;
import com.mindtree.emp.entity.Employee;
import com.mindtree.emp.exception.daoexception.EmployeeInputDaoException;
import com.mindtree.emp.exception.daoexception.EmployeeNotFoundDaoException;
import com.mindtree.emp.exception.daoexception.IntenalServerDaoException;
import com.mindtree.emp.exception.serviceException.EmployeeInputServiceException;
import com.mindtree.emp.exception.serviceException.EmployeeNotFoundServiceException;
import com.mindtree.emp.exception.serviceException.IntenalServerServiceException;
import com.mindtree.emp.util.EmployeeRepository;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	EmployeeRepository emp;

	@Override
	public ResponseEntity<List<Employee>> save()throws EmployeeInputDaoException, IntenalServerDaoException {
		System.out.println(emp.findAll().get(1));
		try {
			if (emp.findAll().get(1)!=null) {
				return new ResponseEntity<List<Employee>>(emp.findAll(), HttpStatus.OK);
			} else {
				throw new EmployeeInputDaoException("No Employess");
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new IntenalServerDaoException("Server error");
		}
	}

	@Override
	public ResponseEntity<String> add(Employee empadd) throws EmployeeInputDaoException, IntenalServerDaoException {
		try {
			if (emp.save(empadd).getEmpName() != null) {
				return new ResponseEntity<>("Employee is Added", HttpStatus.CREATED);
			} else {
				throw new EmployeeInputDaoException("Insertion Error ");
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new IntenalServerDaoException("Server error");
		}
	}

	@Override
	public ResponseEntity<Employee> get(int id) throws EmployeeNotFoundDaoException, EmployeeInputDaoException,IntenalServerDaoException {
		// TODO Auto-generated method stub
		try {
		return new ResponseEntity<>(emp.findById(id).get(), HttpStatus.OK);
		}
		catch (MethodArgumentTypeMismatchException | NumberFormatException e) {
			throw new EmployeeInputDaoException("Please enter integer value ");
		}
		catch (NoSuchElementException e) {
			throw new EmployeeNotFoundDaoException("Employee not found");
		}
		catch (RuntimeException e) {
			throw new IntenalServerDaoException("Server Exception");
		}
	}

	@Override
	public ResponseEntity<String> delete(int id) throws EmployeeNotFoundDaoException {
		Employee deleteEmp =emp.findById(id).orElseThrow( ()->new EmployeeNotFoundDaoException("Employee not found"));
		emp.delete(deleteEmp);
		return new ResponseEntity<>("Employee deleted successfully", HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<String> update(float upsalary, int id) throws EmployeeNotFoundDaoException {
		Employee updatedEmp = emp.findById(id).orElseThrow( ()->new EmployeeNotFoundDaoException("Employee not found"));
		updatedEmp.setEmpSalary(upsalary);
		emp.save(updatedEmp);
		return new ResponseEntity<>("Salary updated successfully", HttpStatus.OK);
	}

}
