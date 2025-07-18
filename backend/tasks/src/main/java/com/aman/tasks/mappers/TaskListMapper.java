package com.aman.tasks.mappers;

import com.aman.tasks.domain.dto.TaskListDto;
import com.aman.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
