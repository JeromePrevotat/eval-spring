package com.humanbooster.evalspring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.humanbooster.evalspring.dto.project.ProjectDTO;
import com.humanbooster.evalspring.dto.task.TaskDTO;
import com.humanbooster.evalspring.mapper.project.ProjectMapper;
import com.humanbooster.evalspring.mapper.task.TaskMapper;
import com.humanbooster.evalspring.model.User;
import com.humanbooster.evalspring.repository.UserRepository;
import com.humanbooster.evalspring.utils.ModelUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;
    private final TaskMapper taskMapper;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<User> deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
        return user;
    }

    @Transactional
    public Optional<User> updateUser(Long id, User newUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    ModelUtil.copyFields(newUser, existingUser);
                    return userRepository.save(existingUser);
                });
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public Optional<List<ProjectDTO>> getUserProjects(Long id) {
        return userRepository.findById(id)
                .map(user -> user.getProjectsList()
                                    .stream()
                                    .map(projectMapper::toDTO)
                                    .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public Optional<List<TaskDTO>> getUserTasks(Long id) {
        return userRepository.findById(id)
                .map(user -> user.getTasksList()
                                    .stream()
                                    .map(taskMapper::toDTO)
                                    .collect(Collectors.toList()));
    }
}
