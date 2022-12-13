package com.nitetrain.service;

import com.nitetrain.service.dto.TrainingUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.nitetrain.domain.TrainingUser}.
 */
public interface TrainingUserService {
    /**
     * Save a trainingUser.
     *
     * @param trainingUserDTO the entity to save.
     * @return the persisted entity.
     */
    TrainingUserDTO save(TrainingUserDTO trainingUserDTO);

    /**
     * Updates a trainingUser.
     *
     * @param trainingUserDTO the entity to update.
     * @return the persisted entity.
     */
    TrainingUserDTO update(TrainingUserDTO trainingUserDTO);

    /**
     * Partially updates a trainingUser.
     *
     * @param trainingUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TrainingUserDTO> partialUpdate(TrainingUserDTO trainingUserDTO);

    /**
     * Get all the trainingUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TrainingUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" trainingUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TrainingUserDTO> findOne(Long id);

    /**
     * Delete the "id" trainingUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
