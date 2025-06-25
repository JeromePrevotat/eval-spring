package com.humanbooster.evalspring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Task title cannot be blank")
    @Column(name = "title")
    String title;

    @NotNull
    @Column(name = "status")
    TaskStatus status;

    @NotNull
    @ManyToOne
    @JsonBackReference("projects-tasks")
    @JoinColumn(name = "project", nullable = false)
    Project project;

    @ManyToOne
    @JsonBackReference("tasks-users")
    @JoinColumn(name = "assignee", nullable = false)
    User assignee;
}
