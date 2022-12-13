package com.nitetrain.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nitetrain.IntegrationTest;
import com.nitetrain.domain.TrainingUser;
import com.nitetrain.repository.TrainingUserRepository;
import com.nitetrain.service.dto.TrainingUserDTO;
import com.nitetrain.service.mapper.TrainingUserMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TrainingUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TrainingUserResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/training-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TrainingUserRepository trainingUserRepository;

    @Autowired
    private TrainingUserMapper trainingUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTrainingUserMockMvc;

    private TrainingUser trainingUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrainingUser createEntity(EntityManager em) {
        TrainingUser trainingUser = new TrainingUser().firstName(DEFAULT_FIRST_NAME).lastName(DEFAULT_LAST_NAME);
        return trainingUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrainingUser createUpdatedEntity(EntityManager em) {
        TrainingUser trainingUser = new TrainingUser().firstName(UPDATED_FIRST_NAME).lastName(UPDATED_LAST_NAME);
        return trainingUser;
    }

    @BeforeEach
    public void initTest() {
        trainingUser = createEntity(em);
    }

    @Test
    @Transactional
    void createTrainingUser() throws Exception {
        int databaseSizeBeforeCreate = trainingUserRepository.findAll().size();
        // Create the TrainingUser
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);
        restTrainingUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeCreate + 1);
        TrainingUser testTrainingUser = trainingUserList.get(trainingUserList.size() - 1);
        assertThat(testTrainingUser.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testTrainingUser.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
    }

    @Test
    @Transactional
    void createTrainingUserWithExistingId() throws Exception {
        // Create the TrainingUser with an existing ID
        trainingUser.setId(1L);
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);

        int databaseSizeBeforeCreate = trainingUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrainingUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = trainingUserRepository.findAll().size();
        // set the field null
        trainingUser.setFirstName(null);

        // Create the TrainingUser, which fails.
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);

        restTrainingUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = trainingUserRepository.findAll().size();
        // set the field null
        trainingUser.setLastName(null);

        // Create the TrainingUser, which fails.
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);

        restTrainingUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTrainingUsers() throws Exception {
        // Initialize the database
        trainingUserRepository.saveAndFlush(trainingUser);

        // Get all the trainingUserList
        restTrainingUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trainingUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)));
    }

    @Test
    @Transactional
    void getTrainingUser() throws Exception {
        // Initialize the database
        trainingUserRepository.saveAndFlush(trainingUser);

        // Get the trainingUser
        restTrainingUserMockMvc
            .perform(get(ENTITY_API_URL_ID, trainingUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(trainingUser.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME));
    }

    @Test
    @Transactional
    void getNonExistingTrainingUser() throws Exception {
        // Get the trainingUser
        restTrainingUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTrainingUser() throws Exception {
        // Initialize the database
        trainingUserRepository.saveAndFlush(trainingUser);

        int databaseSizeBeforeUpdate = trainingUserRepository.findAll().size();

        // Update the trainingUser
        TrainingUser updatedTrainingUser = trainingUserRepository.findById(trainingUser.getId()).get();
        // Disconnect from session so that the updates on updatedTrainingUser are not directly saved in db
        em.detach(updatedTrainingUser);
        updatedTrainingUser.firstName(UPDATED_FIRST_NAME).lastName(UPDATED_LAST_NAME);
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(updatedTrainingUser);

        restTrainingUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, trainingUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeUpdate);
        TrainingUser testTrainingUser = trainingUserList.get(trainingUserList.size() - 1);
        assertThat(testTrainingUser.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testTrainingUser.getLastName()).isEqualTo(UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void putNonExistingTrainingUser() throws Exception {
        int databaseSizeBeforeUpdate = trainingUserRepository.findAll().size();
        trainingUser.setId(count.incrementAndGet());

        // Create the TrainingUser
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrainingUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, trainingUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTrainingUser() throws Exception {
        int databaseSizeBeforeUpdate = trainingUserRepository.findAll().size();
        trainingUser.setId(count.incrementAndGet());

        // Create the TrainingUser
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrainingUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTrainingUser() throws Exception {
        int databaseSizeBeforeUpdate = trainingUserRepository.findAll().size();
        trainingUser.setId(count.incrementAndGet());

        // Create the TrainingUser
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrainingUserMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTrainingUserWithPatch() throws Exception {
        // Initialize the database
        trainingUserRepository.saveAndFlush(trainingUser);

        int databaseSizeBeforeUpdate = trainingUserRepository.findAll().size();

        // Update the trainingUser using partial update
        TrainingUser partialUpdatedTrainingUser = new TrainingUser();
        partialUpdatedTrainingUser.setId(trainingUser.getId());

        restTrainingUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrainingUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTrainingUser))
            )
            .andExpect(status().isOk());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeUpdate);
        TrainingUser testTrainingUser = trainingUserList.get(trainingUserList.size() - 1);
        assertThat(testTrainingUser.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testTrainingUser.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
    }

    @Test
    @Transactional
    void fullUpdateTrainingUserWithPatch() throws Exception {
        // Initialize the database
        trainingUserRepository.saveAndFlush(trainingUser);

        int databaseSizeBeforeUpdate = trainingUserRepository.findAll().size();

        // Update the trainingUser using partial update
        TrainingUser partialUpdatedTrainingUser = new TrainingUser();
        partialUpdatedTrainingUser.setId(trainingUser.getId());

        partialUpdatedTrainingUser.firstName(UPDATED_FIRST_NAME).lastName(UPDATED_LAST_NAME);

        restTrainingUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrainingUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTrainingUser))
            )
            .andExpect(status().isOk());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeUpdate);
        TrainingUser testTrainingUser = trainingUserList.get(trainingUserList.size() - 1);
        assertThat(testTrainingUser.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testTrainingUser.getLastName()).isEqualTo(UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingTrainingUser() throws Exception {
        int databaseSizeBeforeUpdate = trainingUserRepository.findAll().size();
        trainingUser.setId(count.incrementAndGet());

        // Create the TrainingUser
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrainingUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, trainingUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTrainingUser() throws Exception {
        int databaseSizeBeforeUpdate = trainingUserRepository.findAll().size();
        trainingUser.setId(count.incrementAndGet());

        // Create the TrainingUser
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrainingUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTrainingUser() throws Exception {
        int databaseSizeBeforeUpdate = trainingUserRepository.findAll().size();
        trainingUser.setId(count.incrementAndGet());

        // Create the TrainingUser
        TrainingUserDTO trainingUserDTO = trainingUserMapper.toDto(trainingUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrainingUserMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(trainingUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TrainingUser in the database
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTrainingUser() throws Exception {
        // Initialize the database
        trainingUserRepository.saveAndFlush(trainingUser);

        int databaseSizeBeforeDelete = trainingUserRepository.findAll().size();

        // Delete the trainingUser
        restTrainingUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, trainingUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TrainingUser> trainingUserList = trainingUserRepository.findAll();
        assertThat(trainingUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
