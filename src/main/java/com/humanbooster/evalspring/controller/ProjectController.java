package com.humanbooster.evalspring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humanbooster.evalspring.dto.project.ProjectDTO;
import com.humanbooster.evalspring.dto.project.ProjectDTOShort;
import com.humanbooster.evalspring.dto.task.TaskDTO;
import com.humanbooster.evalspring.mapper.project.ProjectMapper;
import com.humanbooster.evalspring.model.Project;
import com.humanbooster.evalspring.service.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projectDTOs = projectService.getAllProjects().stream()
                          .map(projectMapper::toDTO)
                          .collect(Collectors.toList());
        return ResponseEntity.ok(projectDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTOShort> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(projectMapper::toDTOShort)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getProjectTasks(@PathVariable Long id) {
        return projectService.getProjectTasks(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> saveProject(@Valid @RequestBody ProjectDTO projectDTO) {
        Project newProject = projectMapper.toEntity(projectDTO);
        Project savedProject = projectService.saveProject(newProject);
        ProjectDTO savedProjectDTO = projectMapper.toDTO(savedProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProjectDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        return projectService.deleteProjectById(id).isPresent() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(id, projectMapper.toEntity(projectDTO))
                .map(projectMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
