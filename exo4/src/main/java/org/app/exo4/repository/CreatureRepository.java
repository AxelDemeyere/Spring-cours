package org.app.exo4.repository;

import org.app.exo4.model.entity.Creature;
import org.app.exo4.model.entity.CreatureType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreatureRepository extends JpaRepository<Creature, Long> {

    @Query("SELECT c FROM Creature c WHERE " +
            "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:type IS NULL OR c.type = :type) AND " +
            "(:dangerous IS NULL OR c.dangerous = :dangerous) AND " +
            "(:minAge IS NULL OR c.age >= :minAge) AND " +
            "(:maxAge IS NULL OR c.age <= :maxAge) AND " +
            "(:minWeight IS NULL OR c.weight >= :minWeight) AND " +
            "(:maxWeight IS NULL OR c.weight <= :maxWeight)")
    Page<Creature> findWithFilters(
            @Param("name") String name,
            @Param("type") CreatureType type,
            @Param("dangerous") Boolean dangerous,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("minWeight") Double minWeight,
            @Param("maxWeight") Double maxWeight,
            Pageable pageable
    );
}