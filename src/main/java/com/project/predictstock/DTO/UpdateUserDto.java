package com.project.predictstock.DTO;

import com.project.predictstock.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String name;
    private String email;
    private Role role;
}
