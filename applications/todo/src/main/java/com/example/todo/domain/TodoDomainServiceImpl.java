package com.example.todo.domain;

import org.springframework.stereotype.Component;

@Component
public class TodoDomainServiceImpl implements TodoDomainService {

    @Override
    public Todo createTodo(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        return Todo.create(title);
    }

    @Override
    public void completeTodo(Todo todo) {
        if (todo.isCompleted()) {
            throw new IllegalStateException("Already completed");
        }
        todo.complete();
    }
}