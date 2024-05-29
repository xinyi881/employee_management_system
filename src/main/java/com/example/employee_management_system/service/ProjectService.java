package com.example.employee_management_system.service;

import com.example.employee_management_system.entity.Project;
import com.example.employee_management_system.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Cacheable("projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Project not found"));
    }

    public void createProject(Project project) {
        Optional<Project> projectOptional = projectRepository
                .findProjectByName(project.getName());

        if (projectOptional.isPresent()) {
            throw new IllegalStateException("Project already exists");
        }
        projectRepository.save(project);
    }

    public void deleteProject(Long projectId) {
        boolean exists = projectRepository.existsById(projectId);
        if (!exists) {
            throw new IllegalStateException("Project does not exist");
        }
        projectRepository.deleteById(projectId);
    }

    public void updateProject(Long projectId,
                              String name,
                              String description) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project with id " + projectId + " does not found"));

        if (name != null &&
                !name.isEmpty() &&
                !Objects.equals(project.getName(), name)) {
            project.setName(name);
        }

        if (description != null &&
                !description.isEmpty() &&
                !Objects.equals(project.getDescription(), description)) {
            project.setDescription(description);
        }

        projectRepository.save(project);
    }
}
