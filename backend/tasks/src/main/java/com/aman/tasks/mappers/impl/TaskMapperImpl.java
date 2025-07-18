package com.aman.tasks.mappers.impl;

import com.aman.tasks.domain.dto.TasksDto;
import com.aman.tasks.domain.entities.Task;
import com.aman.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TasksDto tasksDto) {
        return new Task(
                tasksDto.id(),
                tasksDto.title(),
                tasksDto.description(),
                tasksDto.dueDate(),
                tasksDto.status(),
                tasksDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TasksDto toDto(Task task) {
        return
                new TasksDto(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        task.getPriority(),
                        task.getStatus()
                );
    }

}
