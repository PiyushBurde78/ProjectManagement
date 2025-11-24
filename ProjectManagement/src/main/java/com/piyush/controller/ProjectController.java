package com.piyush.controller;

import com.piyush.model.Project;
import com.piyush.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Create project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(projectService.createProject(project, token));
    }

    // Get all projects
    @GetMapping
    public ResponseEntity<List<Project>> getProjects(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(projectService.getAllProjects(token));
    }

    // Get project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id,
                                              @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(projectService.getProjectById(id, token));
    }

    // Update project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id,
                                                 @RequestBody Project project,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(projectService.updateProject(id, project, token));
    }

    // Delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id,
                                                @RequestHeader("Authorization") String token) {
        projectService.deleteProject(id, token);
        return ResponseEntity.ok("Project deleted successfully");
    }
}
