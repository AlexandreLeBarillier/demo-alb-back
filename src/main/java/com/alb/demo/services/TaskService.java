package com.alb.demo.services;

import com.alb.demo.entities.Task;
import com.alb.demo.exceptions.UnknownIdException;
import com.alb.demo.models.TaskModel;
import com.alb.demo.models.TaskModelUpdate;
import com.alb.demo.entities.TaskStatus;
import com.alb.demo.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service in charge of tasks
 */
@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Update specific task in DB. Throw UnknownIdException if ID doesn't exist in DB.
     * @param id ID of task to be updated
     * @param taskModel Attributes to be updated
     */
    public void update(final Long id, final TaskModelUpdate taskModel) {
        Optional<Task> taskResult = this.taskRepository.findById(id);

        /* Check if task exists */
        if (taskResult.isEmpty()) {
            logger.error("Task with id {} doesn't exist", id);
            throw new UnknownIdException("Task with id " + id + " doesn't exist");
        }

        Task task = taskResult.get();
        task.setStatus(taskModel.status());

        this.taskRepository.save(task);
    }

    /**
     * Add a new task
     * @param taskModel Task to add
     * @return ID of the created task
     */
    public Long add(final TaskModel taskModel) {
        var task = toEntity(taskModel);
        task = this.taskRepository.save(task);
        return task.getId();
    }

    public List<TaskModel> get(final TaskStatus status) {
        List<Task> taskResult = this.taskRepository.findByStatus(status);
        return fromEntityList(taskResult);
    }

    /**
     * Returns all existing tasks, empty list if no results
     * @return List of tasks
     */
    public List<TaskModel> getAll() {
        var tasks = this.taskRepository.findAll();
        return fromEntityList(tasks);
    }

    /**
     * Returns taskModel if exists, if not returns UnknownIdException
     * @param id Task id
     * @return TaskModel of the selected task
     */
    public TaskModel get(final Long id) {
        var task = this.taskRepository.findById(id);

        if (task.isEmpty()) {
            logger.error("Task with id {} doesn't exist", id);
            throw new UnknownIdException("Task with id " + id + " doesn't exist");
        }

        return fromEntity(task.get());
    }

    /**
     * Map from DB Object to API Model
     * @param task Task entity from db
     * @return model built from entity values
     */
    private static TaskModel fromEntity(final Task task) {
        return new TaskModel(task.getId(), task.getLabel(), task.getStatus());
    }

    /**
     * Map from List of DB Object to list of API Model
     * @param tasks to be converted to models
     * @return List of tasks models to be converted from entity list
     */
    private static List<TaskModel> fromEntityList(final List<Task> tasks) {
        List<TaskModel> taskModels = null;
        if (!tasks.isEmpty()) {
            taskModels = new ArrayList<>();
            for (Task task : tasks) {   
                taskModels.add(fromEntity(task));
            }
        }
        return taskModels;
    }

    /**
     * Map from a task model to an entity task
     * @param taskModel Task to convert
     * @return Entity task built from model values
     */
    private static Task toEntity(final TaskModel taskModel) {
        return new Task(taskModel.getLabel(), taskModel.getStatus());
    }
}
