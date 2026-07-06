package com.tasktracker.backend.controller;

import com.tasktracker.backend.entity.Project;
import com.tasktracker.backend.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository repository;

    public ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Project> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Project create(@Valid @RequestBody Project project) {
        return repository.save(project);
    }
}