package com.nitetrain.web.rest;

import com.nitetrain.repository.BeginnerWorkoutRepository;
import com.nitetrain.service.BeginnerWorkoutService;
import com.nitetrain.service.dto.BeginnerWorkoutDTO;
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
 * REST controller for managing {@link com.nitetrain.domain.BeginnerWorkout}.
 */
@RestController
@RequestMapping("/api")
public class BeginnerWorkoutResource {

    private final Logger log = LoggerFactory.getLogger(BeginnerWorkoutResource.class);

    private static final String ENTITY_NAME = "beginnerWorkout";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BeginnerWorkoutService beginnerWorkoutService;

    private final BeginnerWorkoutRepository beginnerWorkoutRepository;

    public BeginnerWorkoutResource(BeginnerWorkoutService beginnerWorkoutService, BeginnerWorkoutRepository beginnerWorkoutRepository) {
        this.beginnerWorkoutService = beginnerWorkoutService;
        this.beginnerWorkoutRepository = beginnerWorkoutRepository;
    }

    /**
     * {@code POST  /beginner-workouts} : Create a new beginnerWorkout.
     *
     * @param beginnerWorkoutDTO the beginnerWorkoutDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new beginnerWorkoutDTO, or with status {@code 400 (Bad Request)} if the beginnerWorkout has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/beginner-workouts")
    public ResponseEntity<BeginnerWorkoutDTO> createBeginnerWorkout(@Valid @RequestBody BeginnerWorkoutDTO beginnerWorkoutDTO)
        throws URISyntaxException {
        log.debug("REST request to save BeginnerWorkout : {}", beginnerWorkoutDTO);
        if (beginnerWorkoutDTO.getId() != null) {
            throw new BadRequestAlertException("A new beginnerWorkout cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BeginnerWorkoutDTO result = beginnerWorkoutService.save(beginnerWorkoutDTO);
        return ResponseEntity
            .created(new URI("/api/beginner-workouts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /beginner-workouts/:id} : Updates an existing beginnerWorkout.
     *
     * @param id the id of the beginnerWorkoutDTO to save.
     * @param beginnerWorkoutDTO the beginnerWorkoutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beginnerWorkoutDTO,
     * or with status {@code 400 (Bad Request)} if the beginnerWorkoutDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the beginnerWorkoutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/beginner-workouts/{id}")
    public ResponseEntity<BeginnerWorkoutDTO> updateBeginnerWorkout(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BeginnerWorkoutDTO beginnerWorkoutDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BeginnerWorkout : {}, {}", id, beginnerWorkoutDTO);
        if (beginnerWorkoutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, beginnerWorkoutDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!beginnerWorkoutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BeginnerWorkoutDTO result = beginnerWorkoutService.update(beginnerWorkoutDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, beginnerWorkoutDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /beginner-workouts/:id} : Partial updates given fields of an existing beginnerWorkout, field will ignore if it is null
     *
     * @param id the id of the beginnerWorkoutDTO to save.
     * @param beginnerWorkoutDTO the beginnerWorkoutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beginnerWorkoutDTO,
     * or with status {@code 400 (Bad Request)} if the beginnerWorkoutDTO is not valid,
     * or with status {@code 404 (Not Found)} if the beginnerWorkoutDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the beginnerWorkoutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/beginner-workouts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BeginnerWorkoutDTO> partialUpdateBeginnerWorkout(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BeginnerWorkoutDTO beginnerWorkoutDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BeginnerWorkout partially : {}, {}", id, beginnerWorkoutDTO);
        if (beginnerWorkoutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, beginnerWorkoutDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!beginnerWorkoutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BeginnerWorkoutDTO> result = beginnerWorkoutService.partialUpdate(beginnerWorkoutDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, beginnerWorkoutDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /beginner-workouts} : get all the beginnerWorkouts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of beginnerWorkouts in body.
     */
    @GetMapping("/beginner-workouts")
    public ResponseEntity<List<BeginnerWorkoutDTO>> getAllBeginnerWorkouts(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of BeginnerWorkouts");
        Page<BeginnerWorkoutDTO> page = beginnerWorkoutService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /beginner-workouts/:id} : get the "id" beginnerWorkout.
     *
     * @param id the id of the beginnerWorkoutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the beginnerWorkoutDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/beginner-workouts/{id}")
    public ResponseEntity<BeginnerWorkoutDTO> getBeginnerWorkout(@PathVariable Long id) {
        log.debug("REST request to get BeginnerWorkout : {}", id);
        Optional<BeginnerWorkoutDTO> beginnerWorkoutDTO = beginnerWorkoutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beginnerWorkoutDTO);
    }

    /**
     * {@code DELETE  /beginner-workouts/:id} : delete the "id" beginnerWorkout.
     *
     * @param id the id of the beginnerWorkoutDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/beginner-workouts/{id}")
    public ResponseEntity<Void> deleteBeginnerWorkout(@PathVariable Long id) {
        log.debug("REST request to delete BeginnerWorkout : {}", id);
        beginnerWorkoutService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
