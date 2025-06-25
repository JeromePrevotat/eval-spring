package com.humanbooster.evalspring.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotBlank(message = "Project name cannot be blank")
    @Column(name = "name")
    String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    @JsonBackReference("projects-users")
    User creator;

    @JsonManagedReference("projects-tasks")
    @OneToMany(targetEntity = Task.class,
               mappedBy = "project",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    List<Task> tasksList;
}
