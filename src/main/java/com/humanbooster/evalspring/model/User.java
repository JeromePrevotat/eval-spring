package com.humanbooster.evalspring.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username")
    String username;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    String password;

    @NotBlank(message = "Role cannot be blank")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    UserRole role;

    @JsonManagedReference("projects-users")
    @OneToMany(targetEntity= Project.class,
                mappedBy = "creator",
               cascade = CascadeType.ALL)
    List<Project> projectsList;

    @JsonManagedReference("tasks-users")
    @OneToMany(targetEntity= Task.class,
               mappedBy = "assignee",
               cascade = CascadeType.ALL)
    List<Task> tasksList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role.name());
    }

}
