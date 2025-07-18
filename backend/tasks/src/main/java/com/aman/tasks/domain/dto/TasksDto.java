package com.aman.tasks.domain.dto;

import com.aman.tasks.domain.entities.TaskPriority;
import com.aman.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TasksDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
