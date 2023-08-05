package com.codestates.todoapp.service;

import com.codestates.todoapp.entity.Todo;
import com.codestates.todoapp.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    public Todo createTodo(Todo todo ){

        return todoRepository.save(todo);
    }
    public Todo updateTodo( Todo todo ){
        Todo findTodo = findVerifiedTodo(todo.getTodoId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(todoOrder->findTodo.setTodoOrder(todoOrder));
        Optional.ofNullable(todo.getCompleted())
                .ifPresent(completed->findTodo.setCompleted(completed));

        return todoRepository.save(findTodo);
    }
    @Transactional(readOnly = true)
    public Todo findTodo( long todoId ){
        return findVerifiedTodo(todoId);
    }
    @Transactional(readOnly = true)
    public List<Todo> findTodos(){
        List<Todo> todos = todoRepository.findAll();

        return todos;
    }
    public void deleteTodo( long todoId){
        Todo todo = findVerifiedTodo(todoId);
        todoRepository.delete(todo);
    }
    public void deleteTodos(){
        todoRepository.deleteAll();
    }
    @Transactional(readOnly = true)
    public Todo findVerifiedTodo(long todoId){
        Optional<Todo> todoOp = todoRepository.findById(todoId);
        Todo todo = todoOp.orElseThrow(() -> new RuntimeException());

        return todo;
    }

}
