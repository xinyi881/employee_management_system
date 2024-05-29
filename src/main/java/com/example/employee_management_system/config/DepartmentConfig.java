package com.example.employee_management_system.config;

import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepartmentConfig {

    @Bean
    CommandLineRunner commandLineRunner(DepartmentRepository repository) {
        return args -> {
            Department it = new Department(1L, "IT", "Information Technology");
            Department hr = new Department(2L, "HR", "Human Resources");

            repository.saveAll(List.of(it, hr));
        };
    }
}
