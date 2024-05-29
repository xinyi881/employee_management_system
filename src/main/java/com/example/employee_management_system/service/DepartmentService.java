package com.example.employee_management_system.service;

import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Cacheable("departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentId(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalStateException("Department not found"));
    }

    public void addDepartment(Department department) {
        Optional<Department> optionalDepartment = departmentRepository
                .findDepartmentByName(department.getName());
        if (optionalDepartment.isPresent()) {
            throw new RuntimeException("Department already exists");
        }

        departmentRepository.save(department);
    }

    public void deleteDepartmentById(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists){
            throw new RuntimeException("Department with id"+ departmentId +"does not exist");
        }
        departmentRepository.deleteById(departmentId);
    }

    @Transactional
    public void updateDepartment(Long id,
                                 String name,
                                 String description) {
        Department department = departmentRepository
                .findById(id).orElse(null);

        if (name != null &&
                !name.isEmpty() &&
                !Objects.equals(department.getName(), name)) {

            Optional<Department> departmentOptional = departmentRepository
                    .findDepartmentByName(name);

            if (departmentOptional.isPresent()) {
                throw new IllegalStateException("Department already exists");
            }
            department.setName(name);
        }

        if (description != null &&
                !description.isEmpty() &&
                !Objects.equals(department.getDescription(), description)) {
            department.setDescription(description);
        }

        departmentRepository.save(department);

    }
}
