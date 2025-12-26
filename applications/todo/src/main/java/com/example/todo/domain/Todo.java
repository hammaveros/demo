package com.example.todo.domain;

import lombok.Getter;

@Getter
public class Todo {

    private final Long id;
    private final String title;
    private boolean completed;

    private Todo(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public static Todo create(String title) {
        return new Todo(null, title, false);
    }

    public static Todo of(Long id, String title, boolean completed) {
        return new Todo(id, title, completed);
    }

    public void complete() {
        this.completed = true;
    }
}