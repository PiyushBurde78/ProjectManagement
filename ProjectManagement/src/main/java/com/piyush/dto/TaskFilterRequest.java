package com.piyush.dto;


import lombok.Data;
import com.piyush.model.Task;

@Data
public class TaskFilterRequest {
    private String keyword;       // search by title or description
    private Task.Priority priority; 
    private Task.Status status;
}
