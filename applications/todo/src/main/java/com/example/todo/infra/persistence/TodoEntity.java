package com.example.todo.infra.persistence;

import com.example.todo.domain.Todo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean completed;

    private TodoEntity(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public static TodoEntity from(Todo todo) {
        return new TodoEntity(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted()
        );
    }

    public Todo toDomain() {
        return Todo.of(this.id, this.title, this.completed);
    }
}