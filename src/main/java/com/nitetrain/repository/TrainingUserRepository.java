package com.nitetrain.repository;

import com.nitetrain.domain.TrainingUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TrainingUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrainingUserRepository extends JpaRepository<TrainingUser, Long> {}
