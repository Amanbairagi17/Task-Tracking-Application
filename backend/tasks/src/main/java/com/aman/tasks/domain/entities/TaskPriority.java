package com.aman.tasks.domain.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TaskPriority {
    LOW, MEDIUM, HIGH;

    @JsonCreator
    public static TaskPriority fromString(String value) {
        if (value == null) throw new IllegalArgumentException("TaskPriority cannot be null");
        for (TaskPriority priority : TaskPriority.values()) {
            if (priority.name().equalsIgnoreCase(value.trim())) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid TaskPriority: " + value + ". Allowed: LOW, MEDIUM, HIGH.");
    }
}
