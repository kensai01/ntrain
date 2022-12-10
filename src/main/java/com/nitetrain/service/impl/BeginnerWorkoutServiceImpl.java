package com.nitetrain.service.impl;

import com.nitetrain.domain.BeginnerWorkout;
import com.nitetrain.repository.BeginnerWorkoutRepository;
import com.nitetrain.service.BeginnerWorkoutService;
import com.nitetrain.service.dto.BeginnerWorkoutDTO;
import com.nitetrain.service.mapper.BeginnerWorkoutMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BeginnerWorkout}.
 */
@Service
@Transactional
public class BeginnerWorkoutServiceImpl implements BeginnerWorkoutService {

    private final Logger log = LoggerFactory.getLogger(BeginnerWorkoutServiceImpl.class);

    private final BeginnerWorkoutRepository beginnerWorkoutRepository;

    private final BeginnerWorkoutMapper beginnerWorkoutMapper;

    public BeginnerWorkoutServiceImpl(BeginnerWorkoutRepository beginnerWorkoutRepository, BeginnerWorkoutMapper beginnerWorkoutMapper) {
        this.beginnerWorkoutRepository = beginnerWorkoutRepository;
        this.beginnerWorkoutMapper = beginnerWorkoutMapper;
    }

    @Override
    public BeginnerWorkoutDTO save(BeginnerWorkoutDTO beginnerWorkoutDTO) {
        log.debug("Request to save BeginnerWorkout : {}", beginnerWorkoutDTO);
        BeginnerWorkout beginnerWorkout = beginnerWorkoutMapper.toEntity(beginnerWorkoutDTO);
        beginnerWorkout = beginnerWorkoutRepository.save(beginnerWorkout);
        return beginnerWorkoutMapper.toDto(beginnerWorkout);
    }

    @Override
    public BeginnerWorkoutDTO update(BeginnerWorkoutDTO beginnerWorkoutDTO) {
        log.debug("Request to update BeginnerWorkout : {}", beginnerWorkoutDTO);
        BeginnerWorkout beginnerWorkout = beginnerWorkoutMapper.toEntity(beginnerWorkoutDTO);
        beginnerWorkout = beginnerWorkoutRepository.save(beginnerWorkout);
        return beginnerWorkoutMapper.toDto(beginnerWorkout);
    }

    @Override
    public Optional<BeginnerWorkoutDTO> partialUpdate(BeginnerWorkoutDTO beginnerWorkoutDTO) {
        log.debug("Request to partially update BeginnerWorkout : {}", beginnerWorkoutDTO);

        return beginnerWorkoutRepository
            .findById(beginnerWorkoutDTO.getId())
            .map(existingBeginnerWorkout -> {
                beginnerWorkoutMapper.partialUpdate(existingBeginnerWorkout, beginnerWorkoutDTO);

                return existingBeginnerWorkout;
            })
            .map(beginnerWorkoutRepository::save)
            .map(beginnerWorkoutMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BeginnerWorkoutDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BeginnerWorkouts");
        return beginnerWorkoutRepository.findAll(pageable).map(beginnerWorkoutMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BeginnerWorkoutDTO> findOne(Long id) {
        log.debug("Request to get BeginnerWorkout : {}", id);
        return beginnerWorkoutRepository.findById(id).map(beginnerWorkoutMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BeginnerWorkout : {}", id);
        beginnerWorkoutRepository.deleteById(id);
    }
}
