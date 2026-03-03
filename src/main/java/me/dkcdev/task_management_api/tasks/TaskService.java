package me.dkcdev.task_management_api.tasks;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import me.dkcdev.task_management_api.common.UserDetailsImpl;
import me.dkcdev.task_management_api.tasks.dtos.CreateTaskDto;
import me.dkcdev.task_management_api.tasks.dtos.UpdateTaskDto;
import me.dkcdev.task_management_api.tasks.enums.Status;
import me.dkcdev.task_management_api.tasks.models.Task;
import me.dkcdev.task_management_api.users.models.User;
import java.util.UUID;

@Service
public class TaskService {
    private TaskRepository taskRepo;
    // private OrganizationRepository orgRepo;
    // private UserRepository userRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
        // this.userRepo = userRepo;
        // this.orgRepo = orgRepo;
    }

    public List<Task> getAllTasks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetailsImpl) auth.getPrincipal()).getUser();
        return taskRepo.findByOwner_OrganizationId(user.getOrganization().getOrganizationId());
    }

    public Task createTask(CreateTaskDto payload) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetailsImpl) auth.getPrincipal()).getUser();

        Task task = Task.builder()
                .title(payload.title())
                .description(payload.description())
                .owner(user.getOrganization())
                .createdBy(user)
                .status(payload.status() == null ? Status.TODO : payload.status())
                .build();

        return taskRepo.save(task);
    }

    public Task updateTask(UUID id, UpdateTaskDto payload) {
        Task task = taskRepo.findById(id).orElseThrow();

        task.setTitle(payload.title() == null ? task.getTitle() : payload.title());
        task.setDescription(payload.description() == null ? task.getDescription() : payload.description());
        task.setStatus(payload.status() == null ? task.getStatus() : payload.status());

        return taskRepo.save(task);
    }
}
