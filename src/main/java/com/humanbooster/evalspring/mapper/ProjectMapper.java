package com.humanbooster.evalspring.mapper;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.humanbooster.evalspring.dto.ProjectDTO;
import com.humanbooster.evalspring.model.Project;
import com.humanbooster.evalspring.model.Task;
import com.humanbooster.evalspring.repository.TaskRepository;
import com.humanbooster.evalspring.repository.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProjectMapper {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public ProjectDTO toDTO(Project project) {
        if (project == null) return null;
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setCreatorId(project.getCreator() != null ? 
                                project.getCreator().getId() 
                                : null);
        projectDTO.setTasksListIds(project.getTasksList() != null ?
                                     project.getTasksList().stream()
                                             .map(Task::getId)
                                             .collect(Collectors.toList())
                                     : new ArrayList<>());
        return projectDTO;
    }

    public Project toEntity(ProjectDTO projectDTO) {
        if (projectDTO == null) return null;
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setCreator(userRepository.findById(projectDTO.getCreatorId()).orElse(null));
        project.setTasksList(projectDTO.getTasksListIds() != null ?
                             projectDTO.getTasksListIds().stream()
                                    .map(taskRepository::findById)
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .collect(Collectors.toList())
                             : new ArrayList<>());
        return project;
    }
}
