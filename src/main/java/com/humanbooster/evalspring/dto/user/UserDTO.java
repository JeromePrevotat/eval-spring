package com.humanbooster.evalspring.dto.user;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    @NotBlank(message = "Username cannot be blank")
    String username;
    List<Long> projectsListIds;
    List<Long> tasksListIds;
}
