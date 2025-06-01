package com.junit.mockito.junittest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.mockito.junittest.model.Employee;
import com.junit.mockito.junittest.model.MessageDto;
import com.junit.mockito.junittest.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Employee employee;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(employeeController).build();
        employee = new Employee();
        objectMapper = new ObjectMapper();
        employee.setId(1);
        employee.setName("test");
        employee.setPhoneNumber("12345667");
        employee.setAddress("Bihar");
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    void getAllEmployees() throws Exception {
        List<Employee> employeeList = Arrays.asList(employee);
        when(employeeService.getAllEmployee()).thenReturn(employeeList);
        mockMvc.perform(get("/employee/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(employeeList.size()))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[0].phoneNumber").value("12345667"))
                .andExpect(jsonPath("$[0].address").value("Bihar"));
    }

    @Test
    void save() throws Exception {
        MessageDto messageDto = new MessageDto("Employee saved successfully");

        // Mock the service response
        when(employeeService.saveEmployee(any(Employee.class)))
                .thenReturn(messageDto);


        mockMvc.perform(post("/employee/save")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee saved successfully"));
    }

}