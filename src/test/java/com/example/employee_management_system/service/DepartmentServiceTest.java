package com.example.employee_management_system.service;

import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department(4L, "FAFB", "FAFB");
    }

    @Test
    void testAddDepartment() {
        when(departmentRepository.save(department)).thenReturn(department);
        departmentService.addDepartment(department);
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void testGetAllDepartments() {
        departmentService.getAllDepartments();
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testUpdateDepartment() {
        when(departmentRepository.findById(4L)).thenReturn(Optional.of(department));
        departmentService.updateDepartment(4L,"FAFB Updated", null);
        verify(departmentRepository, times(1)).save(department);
        assertEquals("FAFB Updated", department.getName());
    }

    @Test
    void testDeleteDepartment() {
        when(departmentRepository.existsById(4L)).thenReturn(true);
        departmentService.deleteDepartmentById(4L);
        verify(departmentRepository, times(1)).deleteById(4L);
    }
}