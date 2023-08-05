package com.codestates.todoapp.dto;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class TodoResponseDto {
    private Long todoId;
    private String title;
    private Integer todoOrder;
    private Boolean completed;
}