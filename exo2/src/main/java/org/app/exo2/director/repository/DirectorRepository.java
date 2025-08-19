package org.app.exo2.director.repository;

import org.app.exo2.director.model.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
