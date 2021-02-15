package com.mindtree.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindtree.emp.Dao.EmployeeDao;
import com.mindtree.emp.entity.Employee;
import com.mindtree.emp.exception.daoexception.EmployeeInputDaoException;
import com.mindtree.emp.exception.daoexception.EmployeeNotFoundDaoException;
import com.mindtree.emp.exception.daoexception.IntenalServerDaoException;
import com.mindtree.emp.exception.serviceException.EmployeeInputServiceException;
import com.mindtree.emp.exception.serviceException.EmployeeNotFoundServiceException;
import com.mindtree.emp.exception.serviceException.IntenalServerServiceException;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao emp;

	public ResponseEntity<List<Employee>> getEmployees()
			throws EmployeeInputServiceException, IntenalServerServiceException {
		// TODO Auto-generated method stub
		try {
			return emp.save();
		} catch (EmployeeInputDaoException e) {
			// TODO Auto-generated catch block
			throw new EmployeeInputServiceException(e.getLocalizedMessage());
		} catch (IntenalServerDaoException e) {
			// TODO Auto-generated catch block
			throw new IntenalServerServiceException(e.getLocalizedMessage());
		}
	}

	public ResponseEntity<String> addHotel(Employee empadd)
			throws IntenalServerServiceException, EmployeeInputServiceException {
		// TODO Auto-generated method stub
		try {
			return emp.add(empadd);
		} catch (EmployeeInputDaoException e) {
			// TODO Auto-generated catch block
			throw new EmployeeInputServiceException(e.getLocalizedMessage());
		} catch (IntenalServerDaoException e) {
			// TODO Auto-generated catch block
			throw new IntenalServerServiceException(e.getLocalizedMessage());
		}
	}

	public ResponseEntity<Employee> getEmployeeById(int id)
			throws EmployeeNotFoundServiceException, EmployeeInputServiceException, IntenalServerServiceException {
		// TODO Auto-generated method stub
		try {
			return emp.get(id);
		} catch (EmployeeInputDaoException e) {
			// TODO Auto-generated catch block
			throw new EmployeeInputServiceException(e.getLocalizedMessage());
		} catch (IntenalServerDaoException e) {
			// TODO Auto-generated catch block
			throw new IntenalServerServiceException(e);
		} catch (EmployeeNotFoundDaoException e) {
			// TODO Auto-generated catch block
			throw new EmployeeNotFoundServiceException(e);
		}

	}

	public ResponseEntity<String> deleteById(int id) throws EmployeeNotFoundServiceException {
		// TODO Auto-generated method stub
		try {
			return emp.delete(id);
		} catch (EmployeeNotFoundDaoException e) {
			// TODO Auto-generated catch block
			throw new EmployeeNotFoundServiceException(e);
		}
	}

	public ResponseEntity<String> updateSalary(float upsalary, int id) throws EmployeeNotFoundServiceException {
		// TODO Auto-generated method stub
		try {
			return emp.update(upsalary, id);
		} catch (EmployeeNotFoundDaoException e) {
			// TODO Auto-generated catch block
			throw new EmployeeNotFoundServiceException(e);
		}
	}
}
