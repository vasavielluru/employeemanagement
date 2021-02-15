package com.mindtree.emp.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.emp.entity.Employee;


public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

}
             