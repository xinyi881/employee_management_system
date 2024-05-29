package com.example.employee_management_system.repository;

import com.example.employee_management_system.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p WHERE p.name = ?1")
    Optional<Project> findProjectByName(String projectName);
}
