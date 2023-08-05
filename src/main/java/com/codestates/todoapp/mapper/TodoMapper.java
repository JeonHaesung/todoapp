package com.codestates.todoapp.mapper;

import com.codestates.todoapp.dto.TodoRequestDto;
import com.codestates.todoapp.dto.TodoResponseDto;
import com.codestates.todoapp.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface TodoMapper {
    Todo todoRequestDtoToTodo(TodoRequestDto todoRequestDto);
    TodoResponseDto todoToTodoResponseDto(Todo todo);

}
