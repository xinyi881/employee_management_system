package com.example.employee_management_system.service;

import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.Project;
import com.example.employee_management_system.repository.DepartmentRepository;
import com.example.employee_management_system.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
// Represent the absence of value, where returning null might cause unexpected errors
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Cacheable("employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee not found"));
    }

    public void addEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository
                .findEmployeeByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("Employee already exists");
        }

        employeeRepository.save(employee);

//        Optional <Department> departmentOptional = departmentRepository.findById(departmentId);
//        if (departmentOptional.isPresent()) {
//            Department department = departmentOptional.get();
//            employee.setDepartment(department);
//            employeeRepository.save(employee);
//        } else {
//            throw new IllegalStateException("Department not found");
//        }
    }

    public void deleteEmployee(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) {
            throw new IllegalArgumentException("Employee with id " + employeeId + " does not exist");
        }
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(Long employeeId,
                                   String name,
                                   String email,
                                   LocalDate dob,
                                   String position,
                                   Department department,
                                   Set<Project> projects) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException(
                        "Employee with id " + employeeId + " does not exists"
                ));

        if (name != null &&
                !name.isEmpty() &&
                !Objects.equals(employee.getName(), name)) {
            employee.setName(name);
        }

        if (email != null &&
                !email.isEmpty() &&
                !Objects.equals(employee.getEmail(), email)) {
            Optional<Employee> employeeOptional = employeeRepository
                    .findEmployeeByEmail(email);
            if (employeeOptional.isPresent()) {
                throw new IllegalStateException("Email already in use");
            }
            employee.setEmail(email);
        }

        if (dob != null &&
                !Objects.equals(employee.getDob(), dob)) {
            employee.setDob(dob);
        }

        if (position != null &&
                !position.isEmpty() &&
                !Objects.equals(employee.getPosition(), position)) {
            employee.setPosition(position);
        }

        if (department != null &&
                !Objects.equals(employee.getDepartment(), department)) {
            employee.setDepartment(department);
        }

        if (projects != null &&
                !projects.isEmpty() &&
                !Objects.equals(employee.getProjects(), projects)) {
            employee.setProjects(projects);
        }

        employeeRepository.save(employee);
    }
}
