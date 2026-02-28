package me.dkcdev.task_management_api.tasks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dkcdev.task_management_api.tasks.models.Task;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {
    
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}
