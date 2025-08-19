package com.aman.tasks.domain.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TaskStatus {
    OPEN,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    @JsonCreator
    public static TaskStatus fromString(String value) {
        if (value == null) throw new IllegalArgumentException("TaskStatus cannot be null");
        for (TaskStatus status : TaskStatus.values()) {
            if (status.name().equalsIgnoreCase(value.trim().replace(" ", "_"))) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid TaskStatus: " + value + ". Allowed: OPEN, IN_PROGRESS, COMPLETED, CANCELLED.");
    }
}
