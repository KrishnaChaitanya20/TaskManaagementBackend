package me.dkcdev.task_management_api.auth.dtos;

public record LoginDto(
    String email,
    String password
) {}
