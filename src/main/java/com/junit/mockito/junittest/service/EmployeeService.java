package com.junit.mockito.junittest.service;

import com.junit.mockito.junittest.model.Employee;
import com.junit.mockito.junittest.model.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(int id);

    MessageDto saveEmployee(Employee employee);

    ResponseEntity<?> deleteEmployee(int id);

    List<Employee> getAllEmployee();
}
