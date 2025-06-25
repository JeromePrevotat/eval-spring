package com.humanbooster.evalspring.mapper.task;

import org.springframework.stereotype.Component;

import com.humanbooster.evalspring.dto.task.TaskDTO;
import com.humanbooster.evalspring.model.Task;
import com.humanbooster.evalspring.repository.ProjectRepository;
import com.humanbooster.evalspring.repository.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TaskMapper {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    
    public TaskDTO toDTO(Task task) {
        if (task == null) return null;
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setProjectId(task.getProject() != null ?
                            task.getProject().getId()
                            : null);
        taskDTO.setAssigneeId(task.getAssignee() != null ?
                            task.getAssignee().getId()
                            : null);
        return taskDTO;
    }

    public Task toEntity(TaskDTO taskDTO) {
        if (taskDTO == null) return null;
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setStatus(taskDTO.getStatus());
        if (taskDTO.getProjectId() != null) task.setProject(projectRepository.findById(taskDTO.getProjectId()).orElse(null));
        task.setAssignee(userRepository.findById(taskDTO.getAssigneeId()).orElse(null));
        return task;
    }
    
}
