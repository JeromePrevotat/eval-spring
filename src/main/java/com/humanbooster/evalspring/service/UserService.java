package com.humanbooster.evalspring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.humanbooster.evalspring.model.Project;
import com.humanbooster.evalspring.model.User;
import com.humanbooster.evalspring.repository.UserRepository;
import com.humanbooster.evalspring.utils.ModelUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

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
    public Optional<List<String>> getUserProjects(Long id) {
        return userRepository.findById(id)
                .map(user -> user.getProjectsList()
                                    .stream()
                                    .map(Project::getName)
                                    .collect(Collectors.toList()));
    }
}
