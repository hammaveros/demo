package com.example.todo.infra.persistence;

import com.example.todo.domain.Todo;
import com.example.todo.domain.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaTodoRepository implements TodoRepository {

    private final TodoJpaRepository jpaRepository;

    @Override
    public Todo save(Todo todo) {
        TodoEntity entity = TodoEntity.from(todo);
        return jpaRepository.save(entity).toDomain();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return jpaRepository.findById(id)
                .map(TodoEntity::toDomain);
    }

    @Override
    public List<Todo> findAll() {
        return jpaRepository.findAll().stream()
                .map(TodoEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}