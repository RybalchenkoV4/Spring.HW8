package org.example.taskMaster.repository;

import org.example.taskMaster.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
