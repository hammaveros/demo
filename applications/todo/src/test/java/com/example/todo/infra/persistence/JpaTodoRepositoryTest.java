package com.example.todo.infra.persistence;

import com.example.todo.domain.Todo;
import com.example.todo.domain.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaTodoRepository.class)
class JpaTodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    @DisplayName("Todo 저장")
    void save() {
        // given
        Todo todo = Todo.create("테스트 할일");

        // when
        Todo saved = todoRepository.save(todo);

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("테스트 할일");
        assertThat(saved.isCompleted()).isFalse();
    }

    @Test
    @DisplayName("Todo 단건 조회")
    void findById() {
        // given
        Todo todo = Todo.create("조회 테스트");
        Todo saved = todoRepository.save(todo);

        // when
        Optional<Todo> found = todoRepository.findById(saved.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("조회 테스트");
    }

    @Test
    @DisplayName("Todo 전체 조회")
    void findAll() {
        // given
        todoRepository.save(Todo.create("할일 1"));
        todoRepository.save(Todo.create("할일 2"));
        todoRepository.save(Todo.create("할일 3"));

        // when
        List<Todo> todos = todoRepository.findAll();

        // then
        assertThat(todos).hasSize(3);
    }

    @Test
    @DisplayName("Todo 삭제")
    void deleteById() {
        // given
        Todo saved = todoRepository.save(Todo.create("삭제 테스트"));
        Long id = saved.getId();

        // when
        todoRepository.deleteById(id);

        // then
        assertThat(todoRepository.findById(id)).isEmpty();
    }
}