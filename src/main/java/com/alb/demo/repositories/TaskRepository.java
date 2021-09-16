package com.alb.demo.repositories;

import com.alb.demo.entities.Task;
import com.alb.demo.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(final TaskStatus status);
}
