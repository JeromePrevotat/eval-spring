package com.humanbooster.evalspring.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.humanbooster.evalspring.dto.UserDTO;
import com.humanbooster.evalspring.model.Project;
import com.humanbooster.evalspring.model.Task;
import com.humanbooster.evalspring.model.User;
import com.humanbooster.evalspring.repository.ProjectRepository;
import com.humanbooster.evalspring.repository.TaskRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserMapper {
    ProjectRepository projectRepository;
    TaskRepository taskRepository;

    public UserDTO toDTO(User user) {
        if (user == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setProjectsListIds(user.getProjectsList() != null ? 
                                user.getProjectsList()
                                    .stream()
                                    .map(project -> project.getId())
                                    .toList()
                                : null);
        userDTO.setTasksListIds(user.getTasksList() != null ? 
                                user.getTasksList()
                                    .stream()
                                    .map(task -> task.getId())
                                    .toList()
                                : null);
        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) return null;
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        if (userDTO.getProjectsListIds() != null && !userDTO.getProjectsListIds().isEmpty()) {
            user.setProjectsList(new ArrayList<>(
                userDTO.getProjectsListIds()
                .stream()
                .map(projectId -> {
                   Project project = projectRepository.findById(projectId).orElse(null);
                   return project;
               })
                .toList()));
        } else user.setProjectsList(new ArrayList<>());
        if (userDTO.getTasksListIds() != null && !userDTO.getTasksListIds().isEmpty()) {
            user.setTasksList(new ArrayList<>(
                userDTO.getTasksListIds()
                .stream()
                .map(taskId -> {
                   Task task = taskRepository.findById(taskId).orElse(null);
                   return task;
               })
                .toList()));
        } else user.setTasksList(new ArrayList<>());
        return user;
    }
}
