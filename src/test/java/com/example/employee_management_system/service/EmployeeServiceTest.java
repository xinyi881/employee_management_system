package com.example.employee_management_system.service;

import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.Project;
import com.example.employee_management_system.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Department department = new Department (3L, "FOCS", "FOCS");
        Project project = new Project(3L, "Project C", "Description for Project C");
        employee = new Employee(3L,"Cindy", "cindy@example.com", LocalDate.of(2000, Month.AUGUST, 25), "Developer", department, Set.of(project));
    }

    @Test
    void testAddEmployee() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.addEmployee(employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetAllEmployees() {
        employeeService.getAllEmployees();
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEmployee() {
        when(employeeRepository.findById(3L)).thenReturn(Optional.of(employee));
        employeeService.updateEmployee(3L, "Cindy Updated", "cindy.updated@example.com", LocalDate.of(2000, Month.AUGUST, 25), "Senior Developer", employee.getDepartment(), employee.getProjects());
        verify(employeeRepository, times(1)).save(employee);
//  Testing for Error
//        assertThat(employee.getName()).isEqualTo("T Updated");
        assertEquals("Cindy Updated", employee.getName());

    }

    @Test
    void testDeleteEmployee() {
        when(employeeRepository.existsById(3L)).thenReturn(true);
        employeeService.deleteEmployee(3L);
        verify(employeeRepository, times(1)).deleteById(3L);
    }
}