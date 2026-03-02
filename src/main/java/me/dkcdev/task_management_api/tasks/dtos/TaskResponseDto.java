package me.dkcdev.task_management_api.tasks.dtos;

import java.util.UUID;

import me.dkcdev.task_management_api.tasks.enums.Status;
import me.dkcdev.task_management_api.tasks.models.Task;

public record TaskResponseDto(UUID taskId, String title, String description, UUID ownerId, UUID createdById,Status status) {

    public static TaskResponseDto fromTask(Task task) {
        return new TaskResponseDto(
            task.getTaskId(),
            task.getTitle(),
            task.getDescription(),
            task.getOwner().getOrganizationId(),
            task.getCreatedBy().getUserId(),
            task.getStatus()
        );
    }
}
