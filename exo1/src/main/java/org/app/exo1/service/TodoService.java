package org.app.exo1.service;

import jakarta.persistence.EntityNotFoundException;
import org.app.exo1.model.dto.TodoRequestDTO;
import org.app.exo1.model.dto.TodoResponseDTO;
import org.app.exo1.model.entity.Todo;
import org.app.exo1.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponseDTO> getAll() {
        return todoRepository.findAll().stream().map(Todo::toDto).toList();
    }

    public TodoResponseDTO getById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée avec l'id: " + id))
                .toDto();
    }

    public TodoResponseDTO create(TodoRequestDTO requestDTO) {
        return todoRepository.save(requestDTO.toEntity())
                .toDto();
    }

    public TodoResponseDTO update(Long id, TodoRequestDTO requestDTO) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tâche non trouvée avec l'id: " + id));

        todo.setTitle(requestDTO.getTitle());
        todo.setDescription(requestDTO.getDescription());
        todo.setDate(requestDTO.getDate());
        todo.setCompleted(requestDTO.isCompleted());

        return todoRepository.save(todo).toDto();
    }

    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tâche non trouvée avec l'id: " + id);
        }
        todoRepository.deleteById(id);
    }

    public List<TodoResponseDTO> getAllByCompleted(boolean completed) {
        return todoRepository.findAllByCompleted(completed).stream().map(Todo::toDto).toList();
    }
}
