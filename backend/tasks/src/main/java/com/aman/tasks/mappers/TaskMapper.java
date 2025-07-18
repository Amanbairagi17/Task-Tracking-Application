package com.aman.tasks.mappers;

import com.aman.tasks.domain.dto.TasksDto;
import com.aman.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TasksDto tasksDto);

    TasksDto toDto(Task task);
}
