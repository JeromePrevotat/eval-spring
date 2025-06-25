package com.humanbooster.evalspring.mapper.project;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.humanbooster.evalspring.dto.project.ProjectDTO;
import com.humanbooster.evalspring.dto.project.ProjectDTOShort;
import com.humanbooster.evalspring.model.Project;
import com.humanbooster.evalspring.model.Task;
import com.humanbooster.evalspring.model.User;
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
        User creator = project.getCreator();
        projectDTO.setCreatorId(creator != null ? creator.getId() : null);
        projectDTO.setTasksListIds(project.getTasksList() != null ?
                                    project.getTasksList().stream()
                                        .map(Task::getId)
                                        .collect(Collectors.toList())
                                    : new ArrayList<>());
        return projectDTO;
    }

    public ProjectDTOShort toDTOShort(Project project) {
        if (project == null) return null;
        ProjectDTOShort projectDTOShort = new ProjectDTOShort();
        projectDTOShort.setId(project.getId());
        projectDTOShort.setName(project.getName());
        User creator = project.getCreator();
        projectDTOShort.setCreatorId(creator != null ? creator.getId() : null);
        projectDTOShort.setCreatorUsername(creator != null ? creator.getUsername() : null);
        projectDTOShort.setTasksListIds(project.getTasksList() != null ?
                                    project.getTasksList().stream()
                                        .map(Task::getId)
                                        .collect(Collectors.toList())
                                    : new ArrayList<>());
        projectDTOShort.setTasksListNames(project.getTasksList() != null ?
                                     project.getTasksList().stream()
                                             .map(Task::getTitle)
                                             .collect(Collectors.toList())
                                     : new ArrayList<>());
        return projectDTOShort;
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
