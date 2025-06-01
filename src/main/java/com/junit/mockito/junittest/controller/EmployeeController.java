package com.junit.mockito.junittest.controller;

import com.junit.mockito.junittest.Repository.EmployeeRepository;
import com.junit.mockito.junittest.model.Employee;
import com.junit.mockito.junittest.model.MessageDto;
import com.junit.mockito.junittest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get/{id}")
    private Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }
    @PostMapping("/save")
    public MessageDto save(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);

    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return employeeService.deleteEmployee(id);

    }
    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployee();
    }
}
