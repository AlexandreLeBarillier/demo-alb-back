package com.alb.demo.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EnumCodeConverter implements AttributeConverter<TaskStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskStatus status) {
        return status.code();
    }

    @Override
    public TaskStatus convertToEntityAttribute(Integer anInt) {
        return TaskStatus.fromCode(anInt);
    }
}
