package com.humanbooster.evalspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.humanbooster.evalspring.model.Project;
import com.humanbooster.evalspring.repository.ProjectRepository;
import com.humanbooster.evalspring.utils.ModelUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Transactional
    public Optional<Project> deleteProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        project.ifPresent(projectRepository::delete);
        return project;
    }

    @Transactional
    public Optional<Project> updateProject(Long id, Project newProject) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    ModelUtil.copyFields(newProject, existingProject);
                    return projectRepository.save(existingProject);
                });
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }
}
