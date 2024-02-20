package com.example.demo.dtos;

import com.example.demo.domain.Priority;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {
  Integer id;
  @NotBlank
  String name;
  boolean done;
  @NotNull
  Priority priority;
}
