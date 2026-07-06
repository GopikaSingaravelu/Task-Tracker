package com.tasktracker.backend.service;

import com.tasktracker.backend.entity.Task;
import com.tasktracker.backend.entity.TaskPriority;
import com.tasktracker.backend.entity.TaskStatus;
import com.tasktracker.backend.exception.ResourceNotFoundException;
import com.tasktracker.backend.repository.ProjectRepository;
import com.tasktracker.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Page<Task> getTasks(TaskStatus status, TaskPriority priority, Pageable pageable) {
Specification<Task> spec = (root, query, cb) -> cb.conjunction();
        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }
        if (priority != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("priority"), priority));
        }

        return taskRepository.findAll(spec, pageable);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    public Task createTask(Task task) {
        if (task.getProject() == null || task.getProject().getId() == null) {
            throw new ResourceNotFoundException("Project id is required");
        }
        var project = projectRepository.findById(task.getProject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + task.getProject().getId()));
        task.setProject(project);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existing = getTaskById(id);
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setStatus(updatedTask.getStatus());
        existing.setPriority(updatedTask.getPriority());
        existing.setDueDate(updatedTask.getDueDate());
        if (updatedTask.getProject() != null && updatedTask.getProject().getId() != null) {
            var project = projectRepository.findById(updatedTask.getProject().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
            existing.setProject(project);
        }
        return taskRepository.save(existing);
    }

    public Task updateStatus(Long id, TaskStatus status) {
        Task existing = getTaskById(id);
        existing.setStatus(status);
        return taskRepository.save(existing);
    }

    public void deleteTask(Long id) {
        Task existing = getTaskById(id);
        taskRepository.delete(existing);
    }
}