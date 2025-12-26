package com.example.todo.application;

import com.example.todo.application.dto.CreateTodoCommand;
import com.example.todo.domain.Todo;
import com.example.todo.domain.TodoDomainService;
import com.example.todo.domain.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoDomainService todoDomainService;

    @Override
    @Transactional
    public Todo create(CreateTodoCommand command) {
        Todo todo = todoDomainService.createTodo(command.getTitle());
        return todoRepository.save(todo);
    }

    @Override
    public Todo findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    @Transactional
    public Todo complete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));
        todoDomainService.completeTodo(todo);
        return todoRepository.save(todo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}