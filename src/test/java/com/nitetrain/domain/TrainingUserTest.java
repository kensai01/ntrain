package com.nitetrain.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nitetrain.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TrainingUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrainingUser.class);
        TrainingUser trainingUser1 = new TrainingUser();
        trainingUser1.setId(1L);
        TrainingUser trainingUser2 = new TrainingUser();
        trainingUser2.setId(trainingUser1.getId());
        assertThat(trainingUser1).isEqualTo(trainingUser2);
        trainingUser2.setId(2L);
        assertThat(trainingUser1).isNotEqualTo(trainingUser2);
        trainingUser1.setId(null);
        assertThat(trainingUser1).isNotEqualTo(trainingUser2);
    }
}
