package com.piyush.service;

import com.piyush.config.JwtUtil;
import com.piyush.exception.ResourceNotFoundException;
import com.piyush.model.Project;
import com.piyush.model.User;
import com.piyush.repository.ProjectRepository;
import com.piyush.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    
    private String extractUsername(String token) {
        token = token.replace("Bearer ", "").trim();
        return jwtUtil.extractUsername(token);
    }

    
    public Project createProject(Project project, String token) {
        String username = extractUsername(token);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        project.setUser(user);
        return projectRepository.save(project);
    }

    // ---- Get ALL projects of logged-in user ----
    public List<Project> getAllProjects(String token) {
        String username = extractUsername(token);
        return projectRepository.findByUserUsername(username);
    }

    // ---- Get project by ID ----
    public Project getProjectById(Long id, String token) {
        String username = extractUsername(token);

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if (!project.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized access");
        }

        return project;
    }

    // ---- Update Project ----
    public Project updateProject(Long id, Project updatedProject, String token) {
        Project existing = getProjectById(id, token);

        existing.setName(updatedProject.getName());
        existing.setDescription(updatedProject.getDescription());

        return projectRepository.save(existing);
    }

    // ---- Delete Project ----
    public void deleteProject(Long id, String token) {
        Project project = getProjectById(id, token);
        projectRepository.delete(project);
    }
}
