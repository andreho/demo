package com.example.demo.services;

import java.util.List;
import java.util.stream.StreamSupport;

import com.example.demo.beans.TaskDtoToEntityMapper;
import com.example.demo.beans.TaskEntityToDtoMapper;
import com.example.demo.domain.TaskEntity;
import com.example.demo.dtos.TaskDto;
import com.example.demo.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class TaskService {

  final TaskRepository taskRepository;
  final TaskDtoToEntityMapper taskDtoToEntityMapper;
  final TaskEntityToDtoMapper taskEntityToDtoMapper;

  public TaskEntity toEntity(TaskDto dto) {
    return taskDtoToEntityMapper.toEntity(dto);
  }

  public TaskDto toDto(TaskEntity entity) {
    return taskEntityToDtoMapper.toDto(entity);
  }

  @Transactional
  public TaskEntity createTask(TaskEntity entity) {
    if (nonNull(entity.getId())) {
      throw new ResponseStatusException(
        HttpStatusCode.valueOf(400), "A new task cannot have an ID: %s".formatted(entity.getId()));
    }
    return taskRepository.save(entity);
  }

  @Transactional
  public TaskEntity updateTask(TaskEntity entity) {
    requireNonNull(entity.getId());
    var current = getTaskById(entity.getId());
    return taskRepository.save(updateTask(current, entity));
  }

  @Transactional
  public void deleteTask(int id) {
    var current = getTaskById(id);
    taskRepository.delete(current);
  }

  @Transactional
  public List<TaskEntity> findAll() {
    return StreamSupport.stream(taskRepository.findAll().spliterator(), false).toList();
  }

  private TaskEntity updateTask(TaskEntity current, TaskEntity given) {
    //MapStruct or not to MapStruct? That's the question :D
    current.setDone(given.isDone());
    current.setName(given.getName());
    current.setPriority(given.getPriority());
    return current;
  }

  private TaskEntity getTaskById(Integer id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatusCode.valueOf(404), "Task not found: %s".formatted(id)));
  }
}
