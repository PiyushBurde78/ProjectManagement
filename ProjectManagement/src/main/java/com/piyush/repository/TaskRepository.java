package com.piyush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.piyush.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Fetch tasks by projectId
    List<Task> findByProjectId(Long projectId);

    // Optional: Search tasks by title or description (if needed)
    List<Task> findByTitleContainingOrDescriptionContaining(String title, String description);
}
