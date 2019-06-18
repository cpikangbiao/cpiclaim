package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.CaseAssignLog;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.repository.CaseAssignLogRepository;
import com.cpi.claim.service.CaseAssignLogService;
import com.cpi.claim.service.dto.CaseAssignLogDTO;
import com.cpi.claim.service.mapper.CaseAssignLogMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseAssignLogCriteria;
import com.cpi.claim.service.CaseAssignLogQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link CaseAssignLogResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseAssignLogResourceIT {

    private static final String DEFAULT_NUMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGN_USER = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGN_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_ASSIGN_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ASSIGN_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ASSIGNED_USER = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_USER = "BBBBBBBBBB";

    @Autowired
    private CaseAssignLogRepository caseAssignLogRepository;

    @Autowired
    private CaseAssignLogMapper caseAssignLogMapper;

    @Autowired
    private CaseAssignLogService caseAssignLogService;

    @Autowired
    private CaseAssignLogQueryService caseAssignLogQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCaseAssignLogMockMvc;

    private CaseAssignLog caseAssignLog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseAssignLogResource caseAssignLogResource = new CaseAssignLogResource(caseAssignLogService, caseAssignLogQueryService);
        this.restCaseAssignLogMockMvc = MockMvcBuilders.standaloneSetup(caseAssignLogResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseAssignLog createEntity(EntityManager em) {
        CaseAssignLog caseAssignLog = new CaseAssignLog()
            .numberId(DEFAULT_NUMBER_ID)
            .assignUser(DEFAULT_ASSIGN_USER)
            .assignTime(DEFAULT_ASSIGN_TIME)
            .assignedUser(DEFAULT_ASSIGNED_USER);
        return caseAssignLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseAssignLog createUpdatedEntity(EntityManager em) {
        CaseAssignLog caseAssignLog = new CaseAssignLog()
            .numberId(UPDATED_NUMBER_ID)
            .assignUser(UPDATED_ASSIGN_USER)
            .assignTime(UPDATED_ASSIGN_TIME)
            .assignedUser(UPDATED_ASSIGNED_USER);
        return caseAssignLog;
    }

    @BeforeEach
    public void initTest() {
        caseAssignLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseAssignLog() throws Exception {
        int databaseSizeBeforeCreate = caseAssignLogRepository.findAll().size();

        // Create the CaseAssignLog
        CaseAssignLogDTO caseAssignLogDTO = caseAssignLogMapper.toDto(caseAssignLog);
        restCaseAssignLogMockMvc.perform(post("/api/case-assign-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseAssignLogDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseAssignLog in the database
        List<CaseAssignLog> caseAssignLogList = caseAssignLogRepository.findAll();
        assertThat(caseAssignLogList).hasSize(databaseSizeBeforeCreate + 1);
        CaseAssignLog testCaseAssignLog = caseAssignLogList.get(caseAssignLogList.size() - 1);
        assertThat(testCaseAssignLog.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCaseAssignLog.getAssignUser()).isEqualTo(DEFAULT_ASSIGN_USER);
        assertThat(testCaseAssignLog.getAssignTime()).isEqualTo(DEFAULT_ASSIGN_TIME);
        assertThat(testCaseAssignLog.getAssignedUser()).isEqualTo(DEFAULT_ASSIGNED_USER);
    }

    @Test
    @Transactional
    public void createCaseAssignLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseAssignLogRepository.findAll().size();

        // Create the CaseAssignLog with an existing ID
        caseAssignLog.setId(1L);
        CaseAssignLogDTO caseAssignLogDTO = caseAssignLogMapper.toDto(caseAssignLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseAssignLogMockMvc.perform(post("/api/case-assign-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseAssignLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseAssignLog in the database
        List<CaseAssignLog> caseAssignLogList = caseAssignLogRepository.findAll();
        assertThat(caseAssignLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaseAssignLogs() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList
        restCaseAssignLogMockMvc.perform(get("/api/case-assign-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseAssignLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID.toString())))
            .andExpect(jsonPath("$.[*].assignUser").value(hasItem(DEFAULT_ASSIGN_USER.toString())))
            .andExpect(jsonPath("$.[*].assignTime").value(hasItem(DEFAULT_ASSIGN_TIME.toString())))
            .andExpect(jsonPath("$.[*].assignedUser").value(hasItem(DEFAULT_ASSIGNED_USER.toString())));
    }
    
    @Test
    @Transactional
    public void getCaseAssignLog() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get the caseAssignLog
        restCaseAssignLogMockMvc.perform(get("/api/case-assign-logs/{id}", caseAssignLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseAssignLog.getId().intValue()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID.toString()))
            .andExpect(jsonPath("$.assignUser").value(DEFAULT_ASSIGN_USER.toString()))
            .andExpect(jsonPath("$.assignTime").value(DEFAULT_ASSIGN_TIME.toString()))
            .andExpect(jsonPath("$.assignedUser").value(DEFAULT_ASSIGNED_USER.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where numberId equals to DEFAULT_NUMBER_ID
        defaultCaseAssignLogShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the caseAssignLogList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseAssignLogShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCaseAssignLogShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the caseAssignLogList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseAssignLogShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where numberId is not null
        defaultCaseAssignLogShouldBeFound("numberId.specified=true");

        // Get all the caseAssignLogList where numberId is null
        defaultCaseAssignLogShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByAssignUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where assignUser equals to DEFAULT_ASSIGN_USER
        defaultCaseAssignLogShouldBeFound("assignUser.equals=" + DEFAULT_ASSIGN_USER);

        // Get all the caseAssignLogList where assignUser equals to UPDATED_ASSIGN_USER
        defaultCaseAssignLogShouldNotBeFound("assignUser.equals=" + UPDATED_ASSIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByAssignUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where assignUser in DEFAULT_ASSIGN_USER or UPDATED_ASSIGN_USER
        defaultCaseAssignLogShouldBeFound("assignUser.in=" + DEFAULT_ASSIGN_USER + "," + UPDATED_ASSIGN_USER);

        // Get all the caseAssignLogList where assignUser equals to UPDATED_ASSIGN_USER
        defaultCaseAssignLogShouldNotBeFound("assignUser.in=" + UPDATED_ASSIGN_USER);
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByAssignUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where assignUser is not null
        defaultCaseAssignLogShouldBeFound("assignUser.specified=true");

        // Get all the caseAssignLogList where assignUser is null
        defaultCaseAssignLogShouldNotBeFound("assignUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByAssignTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where assignTime equals to DEFAULT_ASSIGN_TIME
        defaultCaseAssignLogShouldBeFound("assignTime.equals=" + DEFAULT_ASSIGN_TIME);

        // Get all the caseAssignLogList where assignTime equals to UPDATED_ASSIGN_TIME
        defaultCaseAssignLogShouldNotBeFound("assignTime.equals=" + UPDATED_ASSIGN_TIME);
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByAssignTimeIsInShouldWork() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where assignTime in DEFAULT_ASSIGN_TIME or UPDATED_ASSIGN_TIME
        defaultCaseAssignLogShouldBeFound("assignTime.in=" + DEFAULT_ASSIGN_TIME + "," + UPDATED_ASSIGN_TIME);

        // Get all the caseAssignLogList where assignTime equals to UPDATED_ASSIGN_TIME
        defaultCaseAssignLogShouldNotBeFound("assignTime.in=" + UPDATED_ASSIGN_TIME);
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByAssignTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where assignTime is not null
        defaultCaseAssignLogShouldBeFound("assignTime.specified=true");

        // Get all the caseAssignLogList where assignTime is null
        defaultCaseAssignLogShouldNotBeFound("assignTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByAssignedUserIsEqualToSomething() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where assignedUser equals to DEFAULT_ASSIGNED_USER
        defaultCaseAssignLogShouldBeFound("assignedUser.equals=" + DEFAULT_ASSIGNED_USER);

        // Get all the caseAssignLogList where assignedUser equals to UPDATED_ASSIGNED_USER
        defaultCaseAssignLogShouldNotBeFound("assignedUser.equals=" + UPDATED_ASSIGNED_USER);
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByAssignedUserIsInShouldWork() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where assignedUser in DEFAULT_ASSIGNED_USER or UPDATED_ASSIGNED_USER
        defaultCaseAssignLogShouldBeFound("assignedUser.in=" + DEFAULT_ASSIGNED_USER + "," + UPDATED_ASSIGNED_USER);

        // Get all the caseAssignLogList where assignedUser equals to UPDATED_ASSIGNED_USER
        defaultCaseAssignLogShouldNotBeFound("assignedUser.in=" + UPDATED_ASSIGNED_USER);
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByAssignedUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        // Get all the caseAssignLogList where assignedUser is not null
        defaultCaseAssignLogShouldBeFound("assignedUser.specified=true");

        // Get all the caseAssignLogList where assignedUser is null
        defaultCaseAssignLogShouldNotBeFound("assignedUser.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseAssignLogsByVesselCaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselCase vesselCase = VesselCaseResourceIT.createEntity(em);
        em.persist(vesselCase);
        em.flush();
        caseAssignLog.setVesselCase(vesselCase);
        caseAssignLogRepository.saveAndFlush(caseAssignLog);
        Long vesselCaseId = vesselCase.getId();

        // Get all the caseAssignLogList where vesselCase equals to vesselCaseId
        defaultCaseAssignLogShouldBeFound("vesselCaseId.equals=" + vesselCaseId);

        // Get all the caseAssignLogList where vesselCase equals to vesselCaseId + 1
        defaultCaseAssignLogShouldNotBeFound("vesselCaseId.equals=" + (vesselCaseId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCaseAssignLogShouldBeFound(String filter) throws Exception {
        restCaseAssignLogMockMvc.perform(get("/api/case-assign-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseAssignLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].assignUser").value(hasItem(DEFAULT_ASSIGN_USER)))
            .andExpect(jsonPath("$.[*].assignTime").value(hasItem(DEFAULT_ASSIGN_TIME.toString())))
            .andExpect(jsonPath("$.[*].assignedUser").value(hasItem(DEFAULT_ASSIGNED_USER)));

        // Check, that the count call also returns 1
        restCaseAssignLogMockMvc.perform(get("/api/case-assign-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCaseAssignLogShouldNotBeFound(String filter) throws Exception {
        restCaseAssignLogMockMvc.perform(get("/api/case-assign-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCaseAssignLogMockMvc.perform(get("/api/case-assign-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCaseAssignLog() throws Exception {
        // Get the caseAssignLog
        restCaseAssignLogMockMvc.perform(get("/api/case-assign-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseAssignLog() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        int databaseSizeBeforeUpdate = caseAssignLogRepository.findAll().size();

        // Update the caseAssignLog
        CaseAssignLog updatedCaseAssignLog = caseAssignLogRepository.findById(caseAssignLog.getId()).get();
        // Disconnect from session so that the updates on updatedCaseAssignLog are not directly saved in db
        em.detach(updatedCaseAssignLog);
        updatedCaseAssignLog
            .numberId(UPDATED_NUMBER_ID)
            .assignUser(UPDATED_ASSIGN_USER)
            .assignTime(UPDATED_ASSIGN_TIME)
            .assignedUser(UPDATED_ASSIGNED_USER);
        CaseAssignLogDTO caseAssignLogDTO = caseAssignLogMapper.toDto(updatedCaseAssignLog);

        restCaseAssignLogMockMvc.perform(put("/api/case-assign-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseAssignLogDTO)))
            .andExpect(status().isOk());

        // Validate the CaseAssignLog in the database
        List<CaseAssignLog> caseAssignLogList = caseAssignLogRepository.findAll();
        assertThat(caseAssignLogList).hasSize(databaseSizeBeforeUpdate);
        CaseAssignLog testCaseAssignLog = caseAssignLogList.get(caseAssignLogList.size() - 1);
        assertThat(testCaseAssignLog.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCaseAssignLog.getAssignUser()).isEqualTo(UPDATED_ASSIGN_USER);
        assertThat(testCaseAssignLog.getAssignTime()).isEqualTo(UPDATED_ASSIGN_TIME);
        assertThat(testCaseAssignLog.getAssignedUser()).isEqualTo(UPDATED_ASSIGNED_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseAssignLog() throws Exception {
        int databaseSizeBeforeUpdate = caseAssignLogRepository.findAll().size();

        // Create the CaseAssignLog
        CaseAssignLogDTO caseAssignLogDTO = caseAssignLogMapper.toDto(caseAssignLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseAssignLogMockMvc.perform(put("/api/case-assign-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseAssignLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseAssignLog in the database
        List<CaseAssignLog> caseAssignLogList = caseAssignLogRepository.findAll();
        assertThat(caseAssignLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseAssignLog() throws Exception {
        // Initialize the database
        caseAssignLogRepository.saveAndFlush(caseAssignLog);

        int databaseSizeBeforeDelete = caseAssignLogRepository.findAll().size();

        // Delete the caseAssignLog
        restCaseAssignLogMockMvc.perform(delete("/api/case-assign-logs/{id}", caseAssignLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CaseAssignLog> caseAssignLogList = caseAssignLogRepository.findAll();
        assertThat(caseAssignLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseAssignLog.class);
        CaseAssignLog caseAssignLog1 = new CaseAssignLog();
        caseAssignLog1.setId(1L);
        CaseAssignLog caseAssignLog2 = new CaseAssignLog();
        caseAssignLog2.setId(caseAssignLog1.getId());
        assertThat(caseAssignLog1).isEqualTo(caseAssignLog2);
        caseAssignLog2.setId(2L);
        assertThat(caseAssignLog1).isNotEqualTo(caseAssignLog2);
        caseAssignLog1.setId(null);
        assertThat(caseAssignLog1).isNotEqualTo(caseAssignLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseAssignLogDTO.class);
        CaseAssignLogDTO caseAssignLogDTO1 = new CaseAssignLogDTO();
        caseAssignLogDTO1.setId(1L);
        CaseAssignLogDTO caseAssignLogDTO2 = new CaseAssignLogDTO();
        assertThat(caseAssignLogDTO1).isNotEqualTo(caseAssignLogDTO2);
        caseAssignLogDTO2.setId(caseAssignLogDTO1.getId());
        assertThat(caseAssignLogDTO1).isEqualTo(caseAssignLogDTO2);
        caseAssignLogDTO2.setId(2L);
        assertThat(caseAssignLogDTO1).isNotEqualTo(caseAssignLogDTO2);
        caseAssignLogDTO1.setId(null);
        assertThat(caseAssignLogDTO1).isNotEqualTo(caseAssignLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseAssignLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseAssignLogMapper.fromId(null)).isNull();
    }
}
