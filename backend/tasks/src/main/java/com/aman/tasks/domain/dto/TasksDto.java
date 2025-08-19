package com.aman.tasks.domain.dto;

import com.aman.tasks.domain.entities.TaskPriority;
import com.aman.tasks.domain.entities.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TasksDto(
        UUID id,
        @NotBlank(message = "Title is required")
        String title,
        String description,
        LocalDateTime dueDate,
        @NotNull(message = "Priority is required")
        TaskPriority priority,
        @NotNull(message = "Status is required")
        TaskStatus status
) {}
