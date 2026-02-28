package me.dkcdev.task_management_api.auth.dtos;

import me.dkcdev.task_management_api.auth.emuns.Roles;

public record RegisterDto(
        String joinToken,
        String name,
        String email,
        String password,
        Roles role) {
}
