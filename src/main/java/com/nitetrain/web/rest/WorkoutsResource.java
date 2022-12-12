package com.nitetrain.web.rest;

import com.nitetrain.repository.WorkoutRepository;
import com.nitetrain.service.WorkoutService;
import com.nitetrain.service.WorkoutsService;
import com.nitetrain.service.dto.WorkoutDTO;
import com.nitetrain.service.dto.WorkoutsDTO;
import com.nitetrain.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.nitetrain.domain.Workout}.
 */
@RestController
@RequestMapping("/api")
public class WorkoutsResource {

    private final Logger log = LoggerFactory.getLogger(WorkoutsResource.class);

    // private static final String ENTITY_NAME = "workout";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkoutsService workoutsService;

    //    private final WorkoutRepository workoutRepository;

    public WorkoutsResource(
        WorkoutsService workoutsService
        // , WorkoutRepository workoutRepository
    ) {
        this.workoutsService = workoutsService;
        //        this.workoutRepository = workoutRepository;
    }

    /**
     * {@code GET  /workouts} : get all the workouts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workouts in body.
     */
    @GetMapping("/pages/workouts")
    public ResponseEntity<List<WorkoutsDTO>> getAllWorkouts() {
        log.debug("REST request to get a page of Workouts");
        List<WorkoutsDTO> page = workoutsService.findAll();
        return ResponseEntity.ok().body(page);
    }
}
