package com.codestates.todoapp.dto;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class TodoRequestDto {
    private String title;
    private Integer todoOrder;
    private Boolean completed;

}
