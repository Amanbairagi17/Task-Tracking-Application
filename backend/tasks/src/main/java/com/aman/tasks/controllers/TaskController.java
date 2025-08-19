package com.aman.tasks.controllers;

import com.aman.tasks.domain.dto.TasksDto;
import com.aman.tasks.domain.entities.Task;
import com.aman.tasks.domain.entities.TaskList;
import com.aman.tasks.mappers.TaskMapper;
import com.aman.tasks.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TasksDto> listTasks(@PathVariable("task_list_id")UUID taskListId){
        System.out.println("Listing tasks for task list: " + taskListId);
        List<Task> tasks = taskService.listTasks(taskListId);
        System.out.println("Found " + tasks.size() + " tasks");
        return tasks.stream()
                .map(task -> {
                    System.out.println("Task ID: " + task.getId() + ", Title: " + task.getTitle());
                    return taskMapper.toDto(task);
                })
                .toList();
    }
    @PostMapping
    public TasksDto createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @Valid @RequestBody TasksDto tasksDto) {
        Task createdTask = taskService.createTask(
                taskListId,
                taskMapper.fromDto(tasksDto)
        );
        return taskMapper.toDto(createdTask);
    }

    @GetMapping(path = "/{task_id}")
    public ResponseEntity<TasksDto> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId
    ){
        System.out.println("Looking for task with ID: " + taskId + " in task list: " + taskListId);
        return taskService.getTask(taskListId,taskId)
                .map(task -> {
                    System.out.println("Found task: " + task.getTitle());
                    return taskMapper.toDto(task);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    System.out.println("Task not found with ID: " + taskId);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping(path = "/{task_id}")
    public TasksDto updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TasksDto tasksDto
    ){
        Task updatedTask = taskService.updateTask(
                taskListId,
                taskId,
                taskMapper.fromDto(tasksDto)
        );
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId
    ){
        taskService.deleteTask(taskListId,taskId);
    }
}
