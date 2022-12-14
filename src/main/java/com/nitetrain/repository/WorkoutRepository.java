package com.nitetrain.repository;

import com.nitetrain.domain.Workout;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Workout entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {}
