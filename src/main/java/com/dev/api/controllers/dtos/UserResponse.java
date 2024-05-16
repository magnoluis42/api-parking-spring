package com.dev.api.controllers.dtos;

import com.dev.api.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private UUID userId;
    private String name;
    private String email;
    private Set<Role> roles;
}
