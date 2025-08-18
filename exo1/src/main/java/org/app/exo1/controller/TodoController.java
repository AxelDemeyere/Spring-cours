package org.app.exo1.controller;

import org.app.exo1.model.dto.TodoRequestDTO;
import org.app.exo1.model.dto.TodoResponseDTO;
import org.app.exo1.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TodoResponseDTO>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<TodoResponseDTO> createTodo(@RequestBody TodoRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(todoService.create(requestDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoResponseDTO> updateTodo(@PathVariable Long id, @RequestBody TodoRequestDTO requestDTO) {
        return ResponseEntity.ok(todoService.update(id, requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok(String.format("Tâche avec l'id %d supprimée", id));
    }
}
