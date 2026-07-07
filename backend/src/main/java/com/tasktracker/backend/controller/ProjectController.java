package com.tasktracker.backend.controller;

import com.tasktracker.backend.entity.Project;
import com.tasktracker.backend.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectRepository.save(project);
    }
}