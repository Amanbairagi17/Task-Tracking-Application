package com.aman.tasks.controllers;

import com.aman.tasks.domain.dto.TasksDto;
import com.aman.tasks.domain.entities.Task;
import com.aman.tasks.mappers.TaskMapper;
import com.aman.tasks.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
    @PostMapping
    public TasksDto createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TasksDto tasksDto){
        Task createdTask = taskService.createTask(
                taskListId,
                taskMapper.fromDto(tasksDto)
        );
        return taskMapper.toDto(createdTask);
    }

}
