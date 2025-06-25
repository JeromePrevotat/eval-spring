package com.humanbooster.evalspring.dto;

import com.humanbooster.evalspring.model.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    Long id;
    @NotBlank(message = "Task title cannot be blank")
    String title;
    @NotNull(message = "Task status cannot be null")
    TaskStatus status;
    @NotNull(message = "Project ID cannot be null")
    Long projectId;
    Long assigneeId;
}
