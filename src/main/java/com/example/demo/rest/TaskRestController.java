package com.example.demo.rest;

import java.util.List;

import com.example.demo.dtos.TaskDto;
import com.example.demo.services.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Validated
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskRestController {

  final TaskService taskService;

  @GetMapping("/tasks")
  public List<TaskDto> tasks() {
    return taskService.findAll()
      .stream()
      .map(taskService::toDto)
      .toList();
  }

  @PostMapping("/task")
  public TaskDto createTask(@RequestBody @Valid TaskDto dto) {
    var entity = taskService.toEntity(dto);
    return taskService.toDto(
      taskService.createTask(entity)
    );
  }

  @PutMapping("/task")
  public TaskDto updateTask(@Valid @RequestBody TaskDto dto) {
    if (isNull(dto.getId())) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Id of the task is not specified (null).");
    }

    var entity = taskService.toEntity(dto);
    return taskService.toDto(
      taskService.updateTask(entity)
    );
  }

  @DeleteMapping("/task/{id}")
  public void deleteTask(@PathVariable @Valid @Min(1) int id) {
    taskService.deleteTask(id);
  }
}
