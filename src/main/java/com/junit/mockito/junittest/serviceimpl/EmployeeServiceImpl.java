package com.junit.mockito.junittest.serviceimpl;

import com.junit.mockito.junittest.Repository.EmployeeRepository;
import com.junit.mockito.junittest.model.Employee;
import com.junit.mockito.junittest.model.MessageDto;
import com.junit.mockito.junittest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public MessageDto saveEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findByName(employee.getName());
        if (existingEmployee != null) {
            throw new RuntimeException("Employee already exists!");
        }


        Employee employee1 = new Employee();
        employee1.setName(employee.getName());
        employee1.setAddress(employee.getAddress());
        employee1.setPhoneNumber(employee.getPhoneNumber());
        employeeRepository.save(employee1);

        return new MessageDto("Employee saved successfully");
    }

    @Override
    public ResponseEntity<?> deleteEmployee(int id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
