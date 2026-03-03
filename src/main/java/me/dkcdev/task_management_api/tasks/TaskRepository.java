package me.dkcdev.task_management_api.tasks;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dkcdev.task_management_api.tasks.models.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task,UUID>{

   List<Task> findByOwner_OrganizationId(UUID organizationId);

}