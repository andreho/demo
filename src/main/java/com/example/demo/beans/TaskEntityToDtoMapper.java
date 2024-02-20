package com.example.demo.beans;

import com.example.demo.domain.TaskEntity;
import com.example.demo.dtos.TaskDto;
import org.springframework.stereotype.Component;

@Component
public class TaskEntityToDtoMapper {
  public TaskDto toDto(TaskEntity task) {
    return TaskDto.builder()
      .id(task.getId())
      .done(task.isDone())
      .priority(task.getPriority())
      .name(task.getName())
      .build();
  }
}
