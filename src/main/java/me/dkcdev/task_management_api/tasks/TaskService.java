package me.dkcdev.task_management_api.tasks;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import me.dkcdev.task_management_api.common.UserDetailsImpl;
import me.dkcdev.task_management_api.tasks.dtos.CreateTaskDto;
import me.dkcdev.task_management_api.tasks.enums.Status;
import me.dkcdev.task_management_api.tasks.models.Task;
import me.dkcdev.task_management_api.users.models.User;

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
        return taskRepo.findAll();
    }

    public Task createTask(CreateTaskDto payload) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetailsImpl) auth.getPrincipal()).getUser();
        
        // Organization org = orgRepo.findById(userDetails.getOrganizationID())
        //         .orElseThrow(() -> new EntityNotFoundException("Organization not found"));

        // User user = userRepo.findById(userDetails.getUserID())
        //         .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Task task = Task.builder()
                .title(payload.title())
                .description(payload.description())
                .owner(user.getOrganization())
                .createdBy(user)
                .status(payload.status() == null ? Status.TODO : payload.status())
                .build();

        return taskRepo.save(task);
    }
}
