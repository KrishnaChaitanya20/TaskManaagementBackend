package me.dkcdev.task_management_api.organizations.dtos;

import java.util.List;
import java.util.UUID;

import me.dkcdev.task_management_api.organizations.models.Organization;
import me.dkcdev.task_management_api.tasks.dtos.TaskResponseDto;
import me.dkcdev.task_management_api.users.dtos.UserResponseDto;

public record OrganizationResponseDto (
    UUID organizationId,
    String organizationName,
    List<UserResponseDto> users,
    List<TaskResponseDto> tasks
){
    public static OrganizationResponseDto fromOrganization(Organization org){
        return new OrganizationResponseDto(
            org.getOrganizationId(),
            org.getOrganizationName(),
            org.getUsers().stream().map(UserResponseDto::fromUser).toList(),
            org.getTasks().stream().map(TaskResponseDto::fromTask).toList()
        );
    }
}