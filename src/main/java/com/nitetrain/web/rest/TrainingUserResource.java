package com.nitetrain.web.rest;

import com.nitetrain.repository.TrainingUserRepository;
import com.nitetrain.service.TrainingUserService;
import com.nitetrain.service.dto.TrainingUserDTO;
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
 * REST controller for managing {@link com.nitetrain.domain.TrainingUser}.
 */
@RestController
@RequestMapping("/api")
public class TrainingUserResource {

    private final Logger log = LoggerFactory.getLogger(TrainingUserResource.class);

    private static final String ENTITY_NAME = "trainingUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrainingUserService trainingUserService;

    private final TrainingUserRepository trainingUserRepository;

    public TrainingUserResource(TrainingUserService trainingUserService, TrainingUserRepository trainingUserRepository) {
        this.trainingUserService = trainingUserService;
        this.trainingUserRepository = trainingUserRepository;
    }

    /**
     * {@code POST  /training-users} : Create a new trainingUser.
     *
     * @param trainingUserDTO the trainingUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trainingUserDTO, or with status {@code 400 (Bad Request)} if the trainingUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/training-users")
    public ResponseEntity<TrainingUserDTO> createTrainingUser(@Valid @RequestBody TrainingUserDTO trainingUserDTO)
        throws URISyntaxException {
        log.debug("REST request to save TrainingUser : {}", trainingUserDTO);
        if (trainingUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new trainingUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrainingUserDTO result = trainingUserService.save(trainingUserDTO);
        return ResponseEntity
            .created(new URI("/api/training-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /training-users/:id} : Updates an existing trainingUser.
     *
     * @param id the id of the trainingUserDTO to save.
     * @param trainingUserDTO the trainingUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trainingUserDTO,
     * or with status {@code 400 (Bad Request)} if the trainingUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trainingUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/training-users/{id}")
    public ResponseEntity<TrainingUserDTO> updateTrainingUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TrainingUserDTO trainingUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TrainingUser : {}, {}", id, trainingUserDTO);
        if (trainingUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trainingUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trainingUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TrainingUserDTO result = trainingUserService.update(trainingUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, trainingUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /training-users/:id} : Partial updates given fields of an existing trainingUser, field will ignore if it is null
     *
     * @param id the id of the trainingUserDTO to save.
     * @param trainingUserDTO the trainingUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trainingUserDTO,
     * or with status {@code 400 (Bad Request)} if the trainingUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the trainingUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the trainingUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/training-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TrainingUserDTO> partialUpdateTrainingUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TrainingUserDTO trainingUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TrainingUser partially : {}, {}", id, trainingUserDTO);
        if (trainingUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trainingUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trainingUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TrainingUserDTO> result = trainingUserService.partialUpdate(trainingUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, trainingUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /training-users} : get all the trainingUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trainingUsers in body.
     */
    @GetMapping("/training-users")
    public ResponseEntity<List<TrainingUserDTO>> getAllTrainingUsers(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TrainingUsers");
        Page<TrainingUserDTO> page = trainingUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /training-users/:id} : get the "id" trainingUser.
     *
     * @param id the id of the trainingUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trainingUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/training-users/{id}")
    public ResponseEntity<TrainingUserDTO> getTrainingUser(@PathVariable Long id) {
        log.debug("REST request to get TrainingUser : {}", id);
        Optional<TrainingUserDTO> trainingUserDTO = trainingUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trainingUserDTO);
    }

    /**
     * {@code DELETE  /training-users/:id} : delete the "id" trainingUser.
     *
     * @param id the id of the trainingUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/training-users/{id}")
    public ResponseEntity<Void> deleteTrainingUser(@PathVariable Long id) {
        log.debug("REST request to delete TrainingUser : {}", id);
        trainingUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
