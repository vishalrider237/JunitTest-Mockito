package com.junit.mockito.junittest.Repository;

import com.junit.mockito.junittest.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {  // this method will run first before test
        employee = new Employee();
        employee.setPhoneNumber("1233455");
        employee.setAddress("Bihar");
        employee.setName("test");
        employeeRepository.save(employee);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.delete(employee);
    }

    @Test
    void findByName() {
        Employee existingemp=employeeRepository.findByName("test");
        assertNotNull(existingemp);
        assertEquals(employee.getName(),existingemp.getName());
    }

    @Test
    void deleteByName() {
        employeeRepository.deleteByName("test");
        Employee existingemp=employeeRepository.findByName("test");
        assertNull(existingemp);
    }
}