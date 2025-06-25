package com.humanbooster.evalspring.dto.project;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    Long id;
    @NotBlank(message = "Project name cannot be blank")
    String name;
    @NotNull(message = "Creator ID cannot be null")
    Long creatorId;
    List<Long> tasksListIds;
}
