package me.dkcdev.task_management_api.tasks.dtos;

import java.util.UUID;

import me.dkcdev.task_management_api.tasks.enums.Status;

public record CreateTaskDto(
    String title,
    String description,
    UUID organizationId,
    UUID createdBy,
    Status status
) {}
