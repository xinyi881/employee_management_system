package com.example.employee_management_system.repository;

import com.example.employee_management_system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d WHERE d.name = ?1")
    Optional<Department> findDepartmentByName(String departmentName);

}
