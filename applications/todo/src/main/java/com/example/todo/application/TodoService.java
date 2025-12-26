package com.example.todo.application;

import com.example.todo.application.dto.CreateTodoCommand;
import com.example.todo.domain.Todo;
import java.util.List;

public interface TodoService {
    Todo create(CreateTodoCommand command);
    Todo findById(Long id);
    List<Todo> findAll();
    Todo complete(Long id);
    void delete(Long id);
}