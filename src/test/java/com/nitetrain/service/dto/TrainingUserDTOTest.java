package com.nitetrain.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.nitetrain.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TrainingUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrainingUserDTO.class);
        TrainingUserDTO trainingUserDTO1 = new TrainingUserDTO();
        trainingUserDTO1.setId(1L);
        TrainingUserDTO trainingUserDTO2 = new TrainingUserDTO();
        assertThat(trainingUserDTO1).isNotEqualTo(trainingUserDTO2);
        trainingUserDTO2.setId(trainingUserDTO1.getId());
        assertThat(trainingUserDTO1).isEqualTo(trainingUserDTO2);
        trainingUserDTO2.setId(2L);
        assertThat(trainingUserDTO1).isNotEqualTo(trainingUserDTO2);
        trainingUserDTO1.setId(null);
        assertThat(trainingUserDTO1).isNotEqualTo(trainingUserDTO2);
    }
}
