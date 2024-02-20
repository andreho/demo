package com.example.demo.beans;

import com.example.demo.domain.TaskEntity;
import com.example.demo.dtos.TaskDto;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoToEntityMapper {
  public TaskEntity toEntity(TaskDto dto) {
    return TaskEntity.builder()
      .id(dto.getId())
      .done(dto.isDone())
      .priority(dto.getPriority())
      .name(dto.getName())
      .build();
  }
}
