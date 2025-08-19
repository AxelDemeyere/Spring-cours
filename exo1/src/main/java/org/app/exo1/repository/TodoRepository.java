package org.app.exo1.repository;


import org.app.exo1.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByCompleted(boolean completed);

}
