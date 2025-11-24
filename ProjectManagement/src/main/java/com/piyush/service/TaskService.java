package com.piyush.service;


import com.piyush.exception.ResourceNotFoundException;
import com.piyush.model.Project;
import com.piyush.model.Task;
import com.piyush.repository.ProjectRepository;
import com.piyush.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Create task under a project
    public Task createTask(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        task.setProject(project);
        return taskRepository.save(task);
    }

    // Get all tasks of a project
    public List<Task> getTasks(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    // Get task by ID
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    // Update task
    public Task updateTask(Long taskId, Task updatedTask) {
        Task task = getTaskById(taskId);

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());

        return taskRepository.save(task);
    }

    // Delete task
    public void deleteTask(Long taskId) {
        Task task = getTaskById(taskId);
        taskRepository.delete(task);
    }
}
