package me.dkcdev.task_management_api.users.dtos;

import java.util.UUID;

import me.dkcdev.task_management_api.users.models.User;

public record UserResponseDto(
    UUID userId,
    String userName,
    String email
){
    public static UserResponseDto fromUser(User user){
        return new UserResponseDto(
            user.getUserId(),
            user.getUserName(),
            user.getEmail()
        );
    }
};