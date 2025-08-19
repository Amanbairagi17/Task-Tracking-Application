package com.aman.tasks.repositories;

import com.aman.tasks.domain.entities.Task;
import com.aman.tasks.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListId(UUID taskListId);  //This is fine — Spring understands it.
    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id);  // FIXED: typo corrected
    void deleteByTaskListIdAndId(UUID taskListId, UUID id); // same here
}
