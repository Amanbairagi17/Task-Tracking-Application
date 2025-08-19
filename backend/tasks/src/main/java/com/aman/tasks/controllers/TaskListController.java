package com.aman.tasks.controllers;

import com.aman.tasks.domain.dto.TaskListDto;
import com.aman.tasks.domain.entities.TaskList;
import com.aman.tasks.mappers.TaskListMapper;
import com.aman.tasks.services.TaskListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        try {
            List<TaskList> taskLists = taskListService.listTaskLists();
            System.out.println("Found " + taskLists.size() + " task lists");
            return taskLists.stream()
                    .map(taskList -> {
                        try {
                            return taskListMapper.toDto(taskList);
                        } catch (Exception e) {
                            System.err.println("Error mapping task list: " + e.getMessage());
                            throw e;
                        }
                    })
                    .toList();
        } catch (Exception e) {
            System.err.println("Error in listTaskLists: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error listing task lists: " + e.getMessage(), e);
        }
    }

    @PostMapping
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        TaskListDto responseDto = taskListMapper.toDto(createdTaskList);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping(path = "/{task_list_id}")
    public ResponseEntity<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
        Optional<TaskList> taskList = taskListService.getTaskList(taskListId);
        return taskList
                .map(list -> ResponseEntity.ok(taskListMapper.toDto(list)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{task_list_id}")
    public ResponseEntity<TaskListDto> updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @Valid @RequestBody TaskListDto taskListDto
    ) {
        // Prevent ID mismatch between path and body
        if (taskListDto.id() != null && !taskListDto.id().equals(taskListId)) {
            return ResponseEntity.badRequest()
                    .body(null); // Optionally add a helpful message using ResponseEntity.of()
        }

        TaskList updatedTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDto)
        );
        return ResponseEntity.ok(taskListMapper.toDto(updatedTaskList));
    }


    @DeleteMapping(path = "/{task_list_id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
        return ResponseEntity.noContent().build();
    }
}
