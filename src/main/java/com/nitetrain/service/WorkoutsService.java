package com.nitetrain.service;

import com.nitetrain.service.dto.WorkoutDTO;
import com.nitetrain.service.dto.WorkoutsDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.nitetrain.domain.Workout}.
 */
public interface WorkoutsService {
    /**
     * Get all the workouts.
     *
     * @return the list of entities.
     */
    List<WorkoutsDTO> findAll();
    /**
     * Get all the WorkoutDTO where BeginnerWorkout is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<WorkoutsDTO> findAllWhereBeginnerWorkoutIsNull();
    /**
     * Get all the WorkoutDTO where IntermediateWorkout is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<WorkoutsDTO> findAllWhereIntermediateWorkoutIsNull();

    /**
     * Get the "id" workout.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkoutsDTO> findOne(Long id);
}
