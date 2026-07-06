package com.tasktracker.backend.controller;

import com.tasktracker.backend.entity.Task;
import com.tasktracker.backend.entity.TaskPriority;
import com.tasktracker.backend.entity.TaskStatus;
import com.tasktracker.backend.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Task> getTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return service.getTasks(status, priority, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id,
                       @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @PatchMapping("/{id}/status")
    public Task updateStatus(@PathVariable Long id,
                             @RequestParam TaskStatus status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTask(id);
    }
}