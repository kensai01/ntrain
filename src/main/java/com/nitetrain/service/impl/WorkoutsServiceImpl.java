package com.nitetrain.service.impl;

import com.nitetrain.domain.BeginnerWorkout;
import com.nitetrain.domain.IntermediateWorkout;
import com.nitetrain.domain.Workout;
import com.nitetrain.domain.WorkoutStep;
import com.nitetrain.repository.BeginnerWorkoutRepository;
import com.nitetrain.repository.IntermediateWorkoutRepository;
import com.nitetrain.repository.WorkoutRepository;
import com.nitetrain.repository.WorkoutStepRepository;
import com.nitetrain.service.WorkoutsService;
import com.nitetrain.service.dto.*;
import com.nitetrain.service.mapper.BeginnerWorkoutMapper;
import com.nitetrain.service.mapper.IntermediateWorkoutMapper;
import com.nitetrain.service.mapper.WorkoutMapper;
import com.nitetrain.service.mapper.WorkoutStepMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Workout}.
 */
@Service
@Transactional
public class WorkoutsServiceImpl implements WorkoutsService {

    private final Logger log = LoggerFactory.getLogger(WorkoutsServiceImpl.class);

    private final WorkoutRepository workoutRepository;

    private final WorkoutMapper workoutMapper;

    private final WorkoutStepMapper workoutStepMapper;

    private final IntermediateWorkoutMapper intermediateWorkoutMapper;

    private final BeginnerWorkoutMapper beginnerWorkoutMapper;

    private final WorkoutStepRepository workoutStepRepository;

    private final BeginnerWorkoutRepository beginnerWorkoutRepository;

    private final IntermediateWorkoutRepository intermediateWorkoutRepository;

    // get workouts
    // -> workout id, title, description, time, scaling
    // get workout steps where workout = workout id
    // get beginner workout where workout id = workout
    // -> id, description
    // get workout steps where beginner workout = beginner workout id
    // -> title, description, step number
    // get intermediate workout where workout id = workout
    // -> id, description
    // get workout steps where beginner workout = beginner workout id
    // -> title, description, step number

    public WorkoutsServiceImpl(
        WorkoutRepository workoutRepository,
        WorkoutMapper workoutMapper,
        WorkoutStepRepository workoutStepRepository,
        BeginnerWorkoutRepository beginnerWorkoutRepository,
        IntermediateWorkoutRepository intermediateWorkoutRepository,
        WorkoutStepMapper workoutStepMapper,
        BeginnerWorkoutMapper beginnerWorkoutMapper,
        IntermediateWorkoutMapper intermediateWorkoutMapper
    ) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.workoutStepRepository = workoutStepRepository;
        this.beginnerWorkoutRepository = beginnerWorkoutRepository;
        this.intermediateWorkoutRepository = intermediateWorkoutRepository;
        this.workoutStepMapper = workoutStepMapper;
        this.beginnerWorkoutMapper = beginnerWorkoutMapper;
        this.intermediateWorkoutMapper = intermediateWorkoutMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutsDTO> findAll() {
        log.debug("Request to get all Workouts");

        List<WorkoutsDTO> workoutsDTOList = new ArrayList<WorkoutsDTO>();

        List<WorkoutDTO> workouts = workoutRepository.findAll().stream().map(workoutMapper::toDto).collect(Collectors.toList());

        workouts.forEach(workout -> {
            WorkoutsDTO workoutsDTO = new WorkoutsDTO();
            workoutsDTO.setDescription(workout.getDescription());
            workoutsDTO.setScaling(workout.getScaling());
            workoutsDTO.setTime(workout.getTime());
            workoutsDTO.setTitle(workout.getTitle());
            workoutsDTO.setVideoId(workout.getVideoId());

            List<WorkoutStepDTO> workoutSteps = workoutStepRepository
                .findByWorkoutId(workout.getId())
                .stream()
                .map(workoutStepMapper::toDto)
                .collect(Collectors.toList());

            workoutsDTO.setWorkoutSteps(workoutSteps);

            BeginnerWorkoutDTO beginnerWorkout = beginnerWorkoutMapper.toDto(beginnerWorkoutRepository.findByWorkoutId(workout.getId()));

            if (beginnerWorkout != null) {
                workoutsDTO.setBeginnerDescription(beginnerWorkout.getDescription());

                List<WorkoutStepDTO> beginnerWorkoutSteps = workoutStepRepository
                    .findByBeginnerWorkoutId(beginnerWorkout.getId())
                    .stream()
                    .map(workoutStepMapper::toDto)
                    .collect(Collectors.toList());

                workoutsDTO.setBeginnerSteps(beginnerWorkoutSteps);
            }

            IntermediateWorkoutDTO intermediateWorkout = intermediateWorkoutMapper.toDto(
                intermediateWorkoutRepository.findByWorkoutId((workout.getId()))
            );

            if (intermediateWorkout != null) {
                workoutsDTO.setIntermediateDescription(intermediateWorkout.getDescription());

                List<WorkoutStepDTO> intermediateWorkoutSteps = workoutStepRepository
                    .findByIntermediateWorkoutId(intermediateWorkout.getId())
                    .stream()
                    .map(workoutStepMapper::toDto)
                    .collect(Collectors.toList());

                workoutsDTO.setIntermidateSteps(intermediateWorkoutSteps);
            }

            workoutsDTOList.add(workoutsDTO);
        });

        return workoutsDTOList;
    }

    /**
     *  Get all the workouts where BeginnerWorkout is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WorkoutsDTO> findAllWhereBeginnerWorkoutIsNull() {
        log.debug("Request to get all workouts where BeginnerWorkout is null");
        return null;
        //        return StreamSupport
        //            .stream(workoutRepository.findAll().spliterator(), false)
        //            .filter(workout -> workout.getBeginnerWorkout() == null)
        //            .map(workoutMapper::toDto)
        //            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the workouts where IntermediateWorkout is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WorkoutsDTO> findAllWhereIntermediateWorkoutIsNull() {
        log.debug("Request to get all workouts where IntermediateWorkout is null");
        return null;
        //        return StreamSupport
        //            .stream(workoutRepository.findAll().spliterator(), false)
        //            .filter(workout -> workout.getIntermediateWorkout() == null)
        //            .map(workoutMapper::toDto)
        //            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkoutsDTO> findOne(Long id) {
        log.debug("Request to get Workout : {}", id);
        return null;
        //        return workoutRepository.findById(id).map(workoutMapper::toDto);
    }
}
