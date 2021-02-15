package com.mindtree.emp.Dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mindtree.emp.entity.Employee;
import com.mindtree.emp.exception.daoexception.EmployeeInputDaoException;
import com.mindtree.emp.exception.daoexception.EmployeeNotFoundDaoException;
import com.mindtree.emp.exception.daoexception.IntenalServerDaoException;
import com.mindtree.emp.exception.serviceException.EmployeeInputServiceException;
import com.mindtree.emp.exception.serviceException.IntenalServerServiceException;

public interface EmployeeDao {

	ResponseEntity<List<Employee>> save() throws EmployeeInputDaoException, IntenalServerDaoException;

	ResponseEntity<String> add(Employee empadd) throws EmployeeInputDaoException, IntenalServerDaoException;

	ResponseEntity<Employee> get(int id) throws EmployeeNotFoundDaoException, EmployeeInputDaoException, IntenalServerDaoException;

	ResponseEntity<String> delete(int id) throws EmployeeNotFoundDaoException;

	ResponseEntity<String> update(float upsalary, int id) throws EmployeeNotFoundDaoException;

}
