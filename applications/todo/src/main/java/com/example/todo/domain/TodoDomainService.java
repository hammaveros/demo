package com.example.todo.domain;

public interface TodoDomainService {
    Todo createTodo(String title);
    void completeTodo(Todo todo);
}