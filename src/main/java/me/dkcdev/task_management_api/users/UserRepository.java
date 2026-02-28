package me.dkcdev.task_management_api.users;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dkcdev.task_management_api.users.models.User;

public interface UserRepository extends JpaRepository<User,UUID> {

    User findByEmail(String email);
    
}
