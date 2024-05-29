package com.example.employee_management_system.config;

import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.Project;
import com.example.employee_management_system.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner employeeCommandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee john = new Employee(
                    "John Doe",
                    "john.doe@example.com",
                    LocalDate.of(1985, Month.MARCH, 25),
                    "Developer",
                    new Department(1L, "IT", null),
                    Set.of(new Project(1L, "Project Alpha", null))
            );

            Employee jane = new Employee(
                    "Jane Smith",
                    "jane.smith@example.com",
                    LocalDate.of(1990, Month.JULY, 15),
                    "Manager",
                    new Department(2L, "HR", null),
                    Set.of(new Project(2L, "Project Beta", null))
            );

            repository.saveAll(List.of(john, jane));
        };
    }
}
