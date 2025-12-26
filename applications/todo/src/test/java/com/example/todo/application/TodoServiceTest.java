package com.example.todo.application;

import com.example.todo.application.dto.CreateTodoCommand;
import com.example.todo.domain.Todo;
import com.example.todo.domain.TodoDomainService;
import com.example.todo.domain.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    private TodoServiceImpl todoService;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private TodoDomainService todoDomainService;

    @Test
    @DisplayName("Todo 생성")
    void create() {
        // given
        CreateTodoCommand command = new CreateTodoCommand("테스트");
        Todo todo = Todo.of(1L, "테스트", false);

        given(todoDomainService.createTodo(anyString())).willReturn(todo);
        given(todoRepository.save(any(Todo.class))).willReturn(todo);

        // when
        Todo result = todoService.create(command);

        // then
        assertThat(result.getTitle()).isEqualTo("테스트");
        verify(todoDomainService).createTodo(anyString());
        verify(todoRepository).save(any());
    }

    @Test
    @DisplayName("Todo 조회 - 존재하는 경우")
    void findById_exists() {
        // given
        Todo todo = Todo.of(1L, "테스트", false);

        given(todoRepository.findById(1L)).willReturn(Optional.of(todo));

        // when
        Todo result = todoService.findById(1L);

        // then
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Todo 조회 - 존재하지 않는 경우")
    void findById_notExists() {
        // given
        given(todoRepository.findById(1L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> todoService.findById(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Todo not found");
    }

    @Test
    @DisplayName("Todo 전체 조회")
    void findAll() {
        // given
        List<Todo> todos = List.of(
                Todo.of(1L, "할일1", false),
                Todo.of(2L, "할일2", false)
        );

        given(todoRepository.findAll()).willReturn(todos);

        // when
        List<Todo> result = todoService.findAll();

        // then
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("Todo 완료 처리")
    void complete() {
        // given
        Todo todo = Todo.of(1L, "테스트", false);

        given(todoRepository.findById(1L)).willReturn(Optional.of(todo));
        given(todoRepository.save(any(Todo.class))).willReturn(todo);

        // when
        todoService.complete(1L);

        // then
        verify(todoDomainService).completeTodo(todo);
        verify(todoRepository).save(todo);
    }

    @Test
    @DisplayName("Todo 삭제")
    void delete() {
        // when
        todoService.delete(1L);

        // then
        verify(todoRepository).deleteById(1L);
    }
}