package com.example.employee_management_system.config;

import com.example.employee_management_system.entity.Project;
import com.example.employee_management_system.repository.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class ProjectConfig {

    @Bean
    CommandLineRunner projectCommandLineRunner(ProjectRepository repository) {
        return args -> {
            Project projectAlpha = new Project(1L, "Project Alpha", "Description for Project Alpha");
            Project projectBeta = new Project(2L, "Project Beta", "Description for Project Beta");

            repository.saveAll(List.of(projectAlpha, projectBeta));
        };
    }
}
