package com.alb.demo.models;

import com.alb.demo.entities.TaskStatus;

/**
 * Task model with only updatable attributes
 */
public record TaskModelUpdate(TaskStatus status) {

}
