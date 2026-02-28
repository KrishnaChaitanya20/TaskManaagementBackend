package me.dkcdev.task_management_api.tasks;

import java.util.List;

import org.springframework.stereotype.Service;

import me.dkcdev.task_management_api.tasks.models.Task;

@Service
public class TaskService {
    private TaskRepository taskRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }


    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }
}
