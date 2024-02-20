package com.example.demo.domain;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("task")
@NoArgsConstructor
@AllArgsConstructor
@AccessType(AccessType.Type.FIELD)
public class TaskEntity {
  @Id
  Integer id;

  @NotBlank
  String name;

  boolean done;

  @CreatedDate
  @ReadOnlyProperty
  Instant created;

  @NotNull
  Priority priority;
}
