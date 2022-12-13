package com.nitetrain.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainingUserMapperTest {

    private TrainingUserMapper trainingUserMapper;

    @BeforeEach
    public void setUp() {
        trainingUserMapper = new TrainingUserMapperImpl();
    }
}
