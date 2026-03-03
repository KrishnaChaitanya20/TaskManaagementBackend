package me.dkcdev.task_management_api.tasks.dtos;


import me.dkcdev.task_management_api.tasks.enums.Status;

public record UpdateTaskDto(
    String title,
    String description,
    Status status
) {}
