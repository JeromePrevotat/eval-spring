package com.humanbooster.evalspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.humanbooster.evalspring.model.Task;
import com.humanbooster.evalspring.repository.TaskRepository;
import com.humanbooster.evalspring.utils.ModelUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public Optional<Task> deleteTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(taskRepository::delete);
        return task;
    }

    @Transactional
    public Optional<Task> updateTask(Long id, Task newTask) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    ModelUtil.copyFields(newTask, existingTask);
                    return taskRepository.save(existingTask);
                });
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }
}
