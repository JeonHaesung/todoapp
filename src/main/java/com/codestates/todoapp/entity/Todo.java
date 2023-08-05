package com.codestates.todoapp.entity;

import lombok.*;

import javax.persistence.*;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor@Builder
@Entity
public class Todo {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    @Column(nullable = true)
    private String title;
    @Column(nullable = true)
    private Integer todoOrder;
    @Column(nullable = true)
    private Boolean completed;
}
