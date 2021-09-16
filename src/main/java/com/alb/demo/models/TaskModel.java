package com.alb.demo.models;

import com.alb.demo.entities.TaskStatus;


/**
 * Full model of API task
 */
public class TaskModel {

    private Long id;
    private String label;
    private TaskStatus status;

    public TaskModel(Long id, String label, TaskStatus status) {
        this.id = id;
        this.label = label;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
