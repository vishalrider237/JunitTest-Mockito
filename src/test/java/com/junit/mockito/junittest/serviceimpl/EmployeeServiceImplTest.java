package com.junit.mockito.junittest.serviceimpl;

import com.junit.mockito.junittest.Repository.EmployeeRepository;
import com.junit.mockito.junittest.model.Employee;
import com.junit.mockito.junittest.model.MessageDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setAddress("Bihar");
        employee.setPhoneNumber("12345678");
        employee.setName("test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getEmployee() {
    }

    @Test
    void saveEmployee() {
        when(employeeRepository.save(org.mockito.ArgumentMatchers.any(Employee.class))).thenReturn(employee);
        MessageDto dto= employeeService.saveEmployee(employee);
        assertNotNull(dto);
        assertEquals("Employee saved successfully", dto.getMessage());
//        when(employeeRepository.findByName(employee.getName())).thenReturn(employee);
//        when(employeeRepository.save(org.mockito.ArgumentMatchers.any(Employee.class)))).thenReturn(employee);
//        ResponseEntity<?> savedCategory=employeeService.saveEmployee(employee);
//        assertNotNull(savedCategory);
//        assertEquals(employee,savedCategory.getBody());
    }
   @Test
   void  alreadyExistsEmployee() {
        when(employeeRepository.findByName(employee.getName())).thenReturn(employee);
        assertThrows(RuntimeException.class, () -> employeeService.saveEmployee(employee));
        verify(employeeRepository,times(0)).save(any(Employee.class));
   }
    @Test
    void deleteEmployee() {
    }

    @Test
    void getAllEmployee() {
    }
}