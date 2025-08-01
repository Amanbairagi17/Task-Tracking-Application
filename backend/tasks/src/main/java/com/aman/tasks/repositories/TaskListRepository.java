package com.aman.tasks.repositories;

import com.aman.tasks.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList,UUID> {
}
