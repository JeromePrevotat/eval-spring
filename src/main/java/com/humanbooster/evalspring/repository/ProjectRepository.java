package com.humanbooster.evalspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humanbooster.evalspring.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
