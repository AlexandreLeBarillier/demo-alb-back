package com.alb.demo.controllers;

import com.alb.demo.entities.TaskStatus;
import com.alb.demo.models.TaskModel;
import com.alb.demo.models.TaskModelUpdate;
import com.alb.demo.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Get all existing tasks
     *
     * @return list of all existing tasks
     */
    @GetMapping("/all")
    public List<TaskModel> all() {
        logger.debug("Get all tasks");
        return this.taskService.getAll();
    }

    /**
     * Get all tasks by status
     *
     * @param status Filter tasks by this status
     */
    @GetMapping
    public List<TaskModel> allByStatus(final @RequestParam("status") TaskStatus status) {
        logger.debug("Get all tasks with status={}", status);
        return this.taskService.get(status);
    }

    /**
     * Get one task by its id
     *
     * @param id ID of the selected task
     */
    @GetMapping("/{id}")
    public TaskModel oneById(final @PathVariable("id") Long id) {
        logger.debug("Get one task with id={}", id);
        return this.taskService.get(id);
    }

    /**
     * Add one Task
     *
     * @param taskModel Task to add
     */
    @PutMapping
    public ResponseEntity<Long> create(final @RequestBody TaskModel taskModel) {
        logger.debug("Get all tasks with status={}", taskModel);
        var id = this.taskService.add(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    /**
     * Update one task
     *
     * @param id        Id of the task to update
     * @param taskModel TaskModel with only updatable attributes
     */
    @PostMapping("/{id}")
    public void update(final @PathVariable Long id,
                       final @RequestBody TaskModelUpdate taskModel) {
        logger.debug("Update task with id {}, model={}", id, taskModel);
        this.taskService.update(id, taskModel);
    }
}
