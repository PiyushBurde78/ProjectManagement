package com.piyush.controller;

import com.piyush.model.Task;
import com.piyush.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // ----------------- Create Task -----------------
    @PostMapping
    public ResponseEntity<Task> createTask(
            @PathVariable Long projectId,
            @RequestBody Task task) {

        Task created = taskService.createTask(projectId, task);
        return ResponseEntity.ok(created);
    }

    // ----------------- Get All Tasks -----------------
    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @PathVariable Long projectId) {

        List<Task> tasks = taskService.getTasks(projectId);
        return ResponseEntity.ok(tasks);
    }

    // ----------------- Get Task By ID -----------------
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId) {

        Task task = taskService.getTaskById(taskId);

        // Optional Safety Check: Ensure task belongs to project
        if (!task.getProject().getId().equals(projectId)) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        return ResponseEntity.ok(task);
    }

    // ----------------- Update Task -----------------
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody Task updatedTask) {

        Task task = taskService.getTaskById(taskId);

        // project-safe update check
        if (!task.getProject().getId().equals(projectId)) {
            return ResponseEntity.status(403).build();
        }

        Task updated = taskService.updateTask(taskId, updatedTask);
        return ResponseEntity.ok(updated);
    }

    // ----------------- Delete Task -----------------
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId) {

        Task task = taskService.getTaskById(taskId);

        if (!task.getProject().getId().equals(projectId)) {
            return ResponseEntity.status(403).body("Task does not belong to this project");
        }

        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
