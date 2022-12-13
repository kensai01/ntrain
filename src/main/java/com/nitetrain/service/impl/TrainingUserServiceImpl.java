package com.nitetrain.service.impl;

import com.nitetrain.domain.TrainingUser;
import com.nitetrain.repository.TrainingUserRepository;
import com.nitetrain.service.TrainingUserService;
import com.nitetrain.service.dto.TrainingUserDTO;
import com.nitetrain.service.mapper.TrainingUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TrainingUser}.
 */
@Service
@Transactional
public class TrainingUserServiceImpl implements TrainingUserService {

    private final Logger log = LoggerFactory.getLogger(TrainingUserServiceImpl.class);

    private final TrainingUserRepository trainingUserRepository;

    private final TrainingUserMapper trainingUserMapper;

    public TrainingUserServiceImpl(TrainingUserRepository trainingUserRepository, TrainingUserMapper trainingUserMapper) {
        this.trainingUserRepository = trainingUserRepository;
        this.trainingUserMapper = trainingUserMapper;
    }

    @Override
    public TrainingUserDTO save(TrainingUserDTO trainingUserDTO) {
        log.debug("Request to save TrainingUser : {}", trainingUserDTO);
        TrainingUser trainingUser = trainingUserMapper.toEntity(trainingUserDTO);
        trainingUser = trainingUserRepository.save(trainingUser);
        return trainingUserMapper.toDto(trainingUser);
    }

    @Override
    public TrainingUserDTO update(TrainingUserDTO trainingUserDTO) {
        log.debug("Request to update TrainingUser : {}", trainingUserDTO);
        TrainingUser trainingUser = trainingUserMapper.toEntity(trainingUserDTO);
        trainingUser = trainingUserRepository.save(trainingUser);
        return trainingUserMapper.toDto(trainingUser);
    }

    @Override
    public Optional<TrainingUserDTO> partialUpdate(TrainingUserDTO trainingUserDTO) {
        log.debug("Request to partially update TrainingUser : {}", trainingUserDTO);

        return trainingUserRepository
            .findById(trainingUserDTO.getId())
            .map(existingTrainingUser -> {
                trainingUserMapper.partialUpdate(existingTrainingUser, trainingUserDTO);

                return existingTrainingUser;
            })
            .map(trainingUserRepository::save)
            .map(trainingUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrainingUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TrainingUsers");
        return trainingUserRepository.findAll(pageable).map(trainingUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TrainingUserDTO> findOne(Long id) {
        log.debug("Request to get TrainingUser : {}", id);
        return trainingUserRepository.findById(id).map(trainingUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TrainingUser : {}", id);
        trainingUserRepository.deleteById(id);
    }
}
