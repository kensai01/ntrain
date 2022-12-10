package com.nitetrain.repository;

import com.nitetrain.domain.BeginnerWorkout;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BeginnerWorkout entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeginnerWorkoutRepository extends JpaRepository<BeginnerWorkout, Long> {}
