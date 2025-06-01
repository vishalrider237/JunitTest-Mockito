package com.junit.mockito.junittest.Repository;

import com.junit.mockito.junittest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByName(String lastName);
    void deleteByName(String Name);
}
