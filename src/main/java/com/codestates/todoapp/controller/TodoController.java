package com.codestates.todoapp.controller;

import com.codestates.todoapp.dto.TodoRequestDto;
import com.codestates.todoapp.dto.TodoResponseDto;
import com.codestates.todoapp.entity.Todo;
import com.codestates.todoapp.mapper.TodoMapper;
import com.codestates.todoapp.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping()
@Validated
public class TodoController {
    private TodoMapper mapper;
    private TodoService todoService;
    private String url = "http://localhost:8080";

    public TodoController(TodoMapper mapper, TodoService todoService) {
        this.mapper = mapper;
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoRequestDto requestDto){
        Todo todo = mapper.todoRequestDtoToTodo(requestDto);

        todo = todoService.createTodo(todo);

        TodoResponseDto responseDto = mapper.todoToTodoResponseDto(todo);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity patchTodo(@PathVariable("id") @Positive long todoId,
                                    @RequestBody TodoRequestDto requestDto){
        Todo todo = mapper.todoRequestDtoToTodo(requestDto);
        todo.setTodoId(todoId);
        Todo todoUpdated = todoService.updateTodo(todo);
        TodoResponseDto responseDto = mapper.todoToTodoResponseDto(todoUpdated);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable("id") @Positive long todoId){
        Todo todo = todoService.findTodo(todoId);
        TodoResponseDto response = mapper.todoToTodoResponseDto(todo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getTodos(){

        List<Todo> response = todoService.findTodos();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") @Positive long todoId){
        todoService.deleteTodo(todoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity deleteTodos(){
        todoService.deleteTodos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
