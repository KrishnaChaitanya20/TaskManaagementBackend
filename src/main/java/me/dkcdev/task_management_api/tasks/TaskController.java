package me.dkcdev.task_management_api.tasks;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import me.dkcdev.task_management_api.tasks.dtos.CreateTaskDto;
import me.dkcdev.task_management_api.tasks.dtos.TaskResponseDto;
import me.dkcdev.task_management_api.tasks.dtos.UpdateTaskDto;
import me.dkcdev.task_management_api.tasks.models.Task;

@RestController
@RequestMapping("tasks")
public class TaskController {
    
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks().stream().map(TaskResponseDto::fromTask).toList();
    }

    @PostMapping()
    public ResponseEntity<Object> createTask(CreateTaskDto payload) {
        
        
        if(payload.title().isBlank()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing title");
        if(payload.description().isBlank()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing description");
        
        Task newTask;
        try {
            newTask = taskService.createTask(payload);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskResponseDto.fromTask(newTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(UpdateTaskDto payload, @PathVariable("id") UUID id) {
        System.out.println(id +"\n"+ payload);
        try {
            Task updatedTask = taskService.updateTask( id, payload);
            return ResponseEntity.ok(TaskResponseDto.fromTask(updatedTask));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }

}
