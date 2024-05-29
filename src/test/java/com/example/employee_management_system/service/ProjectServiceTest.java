package com.example.employee_management_system.service;

import com.example.employee_management_system.entity.Project;
import com.example.employee_management_system.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project = new Project(4L, "Project D", "Description for Project D");
    }

    @Test
    void testCreateProject() {
        when(projectRepository.save(project)).thenReturn(project);
        projectService.createProject(project);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testGetAllProjects() {
        projectService.getAllProjects();
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProject() {
        when(projectRepository.findById(4L)).thenReturn(Optional.of(project));
        projectService.updateProject(4L, "Project D Updated", null);
        verify(projectRepository, times(1)).save(project);
        assertEquals("Project D Updated", project.getName());
    }

    @Test
    void testDeleteProject() {
        when(projectRepository.existsById(4L)).thenReturn(true);
        projectService.deleteProject(4L);
        verify(projectRepository, times(1)).deleteById(4L);
    }
}
