package com.humanbooster.evalspring.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username")
    String username;

    @JsonManagedReference("projects-users")
    @OneToMany(targetEntity= Project.class,
                mappedBy = "creator",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    List<Project> projectsList;

    @JsonManagedReference("tasks-users")
    @OneToMany(targetEntity= Task.class,
               mappedBy = "assignee",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    List<Task> tasksList;
}
